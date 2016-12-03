package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.dtos.ProgramDTO;
import ar.edu.itba.paw.dtos.ProgramListDTO;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.Program;
import ar.edu.itba.paw.models.builders.ProgramBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Component
@Path("/api/v1/programs")
public class ProgramController {

    private final ProgramService ps;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public ProgramController(ProgramService ps) {
        this.ps = ps;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listPrograms() {
        final List<Program> programs = ps.getAll();
        return Response.ok(new ProgramListDTO(programs)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) {
        final Program program = ps.findById(id);
        if (program != null) {
            return Response.ok(new ProgramDTO(program)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(final ProgramDTO programDTO) {

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

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        ps.delete(id);
        return Response.noContent().build();
    }
}
