package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.ExpandedCourseDTO;
import ar.edu.itba.paw.dtos.ExpandedCourseListDTO;
import ar.edu.itba.paw.dtos.ProgramDTO;
import ar.edu.itba.paw.dtos.ProgramListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;
import ar.edu.itba.paw.models.builders.ProgramBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Path("/v1/programs")
public class ProgramController {

    private final ProgramService ps;

    private final CourseService cs;

    private final ClientService clientService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public ProgramController(ProgramService ps, CourseService cs, ClientService clientService) {
        this.ps = ps;
        this.cs = cs;
        this.clientService = clientService;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listPrograms() {
        final List<Program> programs = ps.getAll();
        return Response.ok(new ProgramListDTO(programs)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) throws Http404Exception {
        final Program program = ps.findById(id);
        if (program != null) {
            return Response.ok(new ProgramDTO(program)).build();
        } else {
            throw new Http404Exception("Program not found");
        }
    }

    @GET
    @Path("/{id}/courses")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listCourses(@PathParam("id") final long id) throws Http404Exception {
        final Map<Integer, List<Course>> courses = cs.findByProgramId(id);

        if (courses == null) {
            throw new Http404Exception("Program not found");
        }

        final List<ExpandedCourseDTO> courseDTOList = new ArrayList<>();

        for (Map.Entry<Integer, List<Course>> entry : courses.entrySet()) {
            for (Course course : entry.getValue()) {
                final ExpandedCourseDTO courseDTO = new ExpandedCourseDTO(course);
                courseDTO.setSemester(entry.getKey());
                courseDTOList.add(courseDTO);
            }
        }

        return Response.ok(new ExpandedCourseListDTO(courseDTOList)).build();

    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final ProgramDTO programDTO) throws HttpException, ValidationException {


        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        validateProgram(programDTO);

        final Program program = ps.create(
                new ProgramBuilder()
                        .setGroup(programDTO.getGroup())
                        .setName(programDTO.getName())
                        .setShortName(programDTO.getShortName())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(program.getProgramid())).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@PathParam("id") final long id,
                           @Valid final ProgramDTO programDTO) throws HttpException, ValidationException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        final Program program = ps.findById(id);

        if (program == null) {
            throw new Http404Exception("Program not found");
        }

        validateProgram(programDTO);

        ps.update(
                id,
                new ProgramBuilder()
                        .setGroup(programDTO.getGroup())
                        .setName(programDTO.getName())
                        .setShortName(programDTO.getShortName())
                        .createModel()
        );

        return Response.ok(new ProgramDTO(ps.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) throws HttpException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        ps.delete(id);
        return Response.noContent().build();
    }

    private void validateProgram(final ProgramDTO programDTO) throws ValidationException {

        if (programDTO.getName() == null || programDTO.getName().isEmpty()) {
            throw new ValidationException(20, "Name can't be empty", "name");
        }

        if (programDTO.getShortName() == null || programDTO.getShortName().isEmpty()) {
            throw new ValidationException(21, "Short name can't be empty", "shortname");
        }
    }
}
