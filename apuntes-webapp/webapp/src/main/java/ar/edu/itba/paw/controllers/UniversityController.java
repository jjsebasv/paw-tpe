package ar.edu.itba.paw.controllers;


import ar.edu.itba.paw.dtos.UniversityDTO;
import ar.edu.itba.paw.dtos.UniversityListDTO;
import ar.edu.itba.paw.interfaces.UniversityService;
import ar.edu.itba.paw.models.University;
import ar.edu.itba.paw.models.builders.UniversityBuilder;
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
@Path("/api/v1/universities")
public class UniversityController {
    private final UniversityService us;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public UniversityController(UniversityService us) {
        this.us = us;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listUniversities() {
        final List<University> programs = us.getAll();
        return Response.ok(new UniversityListDTO(programs)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) {
        final University university = us.findById(id);
        if (university != null) {
            return Response.ok(new UniversityDTO(university)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(final UniversityDTO universityDTO) {

        final University university = us.create(
                new UniversityBuilder()
                        .setName(universityDTO.getName())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(university.getUniversityId())).build();
        return Response.created(uri).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        us.delete(id);
        return Response.noContent().build();
    }
}
