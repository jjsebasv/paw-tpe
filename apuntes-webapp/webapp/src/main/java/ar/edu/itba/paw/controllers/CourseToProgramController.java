package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.CourseToProgramRelationDTO;
import ar.edu.itba.paw.dtos.CourseToProgramRelationListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseProgramRelationService;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/v1/c2p")
@Component
public class CourseToProgramController {

    @Context
    private UriInfo uriInfo;

    private CourseProgramRelationService relationService;

    private final ClientService clientService;
    private final ProgramService ps;
    private final CourseService cs;

    @Autowired
    public CourseToProgramController(CourseProgramRelationService relationService, ClientService clientService, ProgramService ps, CourseService cs) {
        this.relationService = relationService;
        this.clientService = clientService;
        this.ps = ps;
        this.cs = cs;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response get(@DefaultValue("") @QueryParam("courseId") final String courseId,
                        @DefaultValue("") @QueryParam("programId") final String programId) throws HttpException {

        if (courseId.isEmpty() || programId.isEmpty()) {
            final List<CourseProgramRelation> relations = relationService.getAll();
            return Response.ok(new CourseToProgramRelationListDTO(relations)).build();
        } else {

            final CourseProgramRelation relation = relationService.findById(Long.valueOf(programId), Long.valueOf(courseId));
            if (relation != null) {
                return Response.ok(new CourseToProgramRelationDTO(relation)).build();
            } else {
                throw new Http404Exception("Relationship not found");
            }
        }
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(final CourseToProgramRelationDTO relationDTO) throws HttpException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        final Program program = ps.findById(relationDTO.getProgramId());

        if (program == null) {
            throw new Http404Exception("Program not found");
        }

        final Course course = cs.findById(relationDTO.getCourseId());

        if (course == null) {
            throw new Http404Exception("Course not found");
        }

        final CourseProgramRelation relation = relationService.create(
                new CourseProgramRelation(program, course, relationDTO.getSemester())
        );

        return Response.ok(new CourseToProgramRelationDTO(relation)).build();
    }

    @PUT
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@DefaultValue("") @QueryParam("courseId") final String courseId,
                           @DefaultValue("") @QueryParam("programId") final String programId,
                           @QueryParam("semester") final int semester) throws HttpException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        if (courseId.isEmpty() || programId.isEmpty()) {
            throw new Http404Exception("Relationship not found");
        }

        final CourseProgramRelation relation = relationService.findById(Long.valueOf(programId), Long.valueOf(courseId));

        if (relation == null) {
            throw new Http404Exception("Relationship not found");
        }

        relationService.update(
                Long.valueOf(courseId), Long.valueOf(programId), semester
        );

        return Response.ok(new CourseToProgramRelationDTO(relationService.findById(Long.valueOf(programId), Long.valueOf(courseId)))).build();
    }

    @DELETE
    @Produces(value = {MediaType.APPLICATION_JSON,})
    @Consumes("application/x-www-form-urlencoded")
    public Response deleteById(@FormParam("courseId") final long courseId,
                               @FormParam("programId") final long programId) throws HttpException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        final Program program = ps.findById(programId);

        if (program == null) {
            throw new Http404Exception("Program not found");
        }

        final Course course = cs.findById(courseId);

        if (course == null) {
            throw new Http404Exception("Course not found");
        }

        relationService.delete(programId, courseId);
        return Response.noContent().build();
    }
}
