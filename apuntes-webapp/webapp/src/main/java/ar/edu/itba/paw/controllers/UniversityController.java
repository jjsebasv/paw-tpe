package ar.edu.itba.paw.controllers;


import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.ProgramListDTO;
import ar.edu.itba.paw.dtos.UniversityDTO;
import ar.edu.itba.paw.dtos.UniversityListDTO;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.interfaces.UniversityService;
import ar.edu.itba.paw.models.Program;
import ar.edu.itba.paw.models.University;
import ar.edu.itba.paw.models.builders.UniversityBuilder;
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
@Path("/api/v1/universities")
public class UniversityController {
    private final UniversityService us;

    private final ProgramService ps;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public UniversityController(UniversityService us, ProgramService ps) {
        this.us = us;
        this.ps = ps;
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listUniversities() {
        final List<University> programs = us.getAll();
        return Response.ok(new UniversityListDTO(programs)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) throws Http404Exception {
        final University university = us.findById(id);
        if (university != null) {
            return Response.ok(new UniversityDTO(university)).build();
        } else {
            throw new Http404Exception("University not found");
        }
    }

    @GET
    @Path("/{id}/programs")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listPrograms(@PathParam("id") final long id) throws Http404Exception {
        final List<Program> programs = ps.getByUniversityId(id);
        if (programs != null) {
            return Response.ok(new ProgramListDTO(programs)).build();
        } else {
            throw new Http404Exception("University not found");
        }
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final UniversityDTO universityDTO) {

        final University university = us.create(
                new UniversityBuilder()
                        .setName(universityDTO.getName())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(university.getUniversityId())).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@PathParam("id") final long id,
                           @Valid final UniversityDTO universityDTO) throws Http404Exception {

        final University university = us.findById(id);

        if (university == null) {
            throw new Http404Exception("University not found");
        }

        us.update(
                id,
                new UniversityBuilder()
                        .setName(universityDTO.getName())
                        .createModel()
        );

        return Response.ok(new UniversityDTO(us.findById(id))).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        us.delete(id);
        return Response.noContent().build();
    }
}
