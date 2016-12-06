package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.Secured;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.ProgramDTO;
import ar.edu.itba.paw.dtos.ProgramListDTO;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.ClientRole;
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

    @POST
    @Secured({ClientRole.ROLE_ADMIN})
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

    @DELETE
    @Path("/{id}")
    @Secured({ClientRole.ROLE_ADMIN})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        ps.delete(id);
        return Response.noContent().build();
    }
}
