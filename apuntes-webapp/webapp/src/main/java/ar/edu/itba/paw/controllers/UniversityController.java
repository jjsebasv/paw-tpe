package ar.edu.itba.paw.controllers;


import ar.edu.itba.paw.auth.Secured;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.UniversityDTO;
import ar.edu.itba.paw.dtos.UniversityListDTO;
import ar.edu.itba.paw.interfaces.UniversityService;
import ar.edu.itba.paw.models.ClientRole;
import ar.edu.itba.paw.models.University;
import ar.edu.itba.paw.models.builders.UniversityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
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

    @POST
    @Secured({ClientRole.ROLE_ADMIN})
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
    @Secured({ClientRole.ROLE_ADMIN})
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
    @Secured({ClientRole.ROLE_ADMIN})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        us.delete(id);
        return Response.noContent().build();
    }
}
