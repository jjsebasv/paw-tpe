package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.CourseListDTO;
import ar.edu.itba.paw.dtos.ProgramDTO;
import ar.edu.itba.paw.dtos.ProgramListDTO;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.ProgramService;
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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/api/v1/programs")
public class ProgramController {

    private final ProgramService ps;

    private final CourseService cs;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public ProgramController(ProgramService ps, CourseService cs) {
        this.ps = ps;
        this.cs = cs;
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
        final List<Course> courses = cs.findByProgramId(id).values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        if (courses != null) {
            return Response.ok(new CourseListDTO(courses)).build();
        } else {
            throw new Http404Exception("Program not found");
        }
    }

    @POST
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final ProgramDTO programDTO) {

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
                           @Valid final ProgramDTO programDTO) throws Http404Exception {

        final Program program = ps.findById(id);

        if (program == null) {
            throw new Http404Exception("Program not found");
        }

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
    public Response deleteById(@PathParam("id") final long id) {
        ps.delete(id);
        return Response.noContent().build();
    }
}
