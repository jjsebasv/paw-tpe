package ar.edu.itba.paw.controllers;


import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.ProgramListDTO;
import ar.edu.itba.paw.dtos.UniversityDTO;
import ar.edu.itba.paw.dtos.UniversityListDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.interfaces.UniversityService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
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
@Path("/v1/universities")
public class UniversityController {

    private final UniversityService us;

    private final ProgramService ps;

    private final ClientService clientService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public UniversityController(UniversityService us, ProgramService ps, ClientService clientService) {
        this.us = us;
        this.ps = ps;
        this.clientService = clientService;
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
            throw new Http404Exception("Program not found");
        }
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final UniversityDTO universityDTO) throws HttpException, ValidationException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        validateUniversity(universityDTO);

        final University university = us.create(
                new UniversityBuilder()
                        .setName(universityDTO.getName())
                        .setDomain(universityDTO.getDomain())
                        .createModel()
        );

        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(university.getUniversityId())).build();
        Response r = Response.created(uri).build();
        return r;
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response update(@PathParam("id") final long id,
                           @Valid final UniversityDTO universityDTO) throws HttpException, ValidationException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        final University university = us.findById(id);

        if (university == null) {
            throw new Http404Exception("University not found");
        }

        validateUniversity(universityDTO);

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
    public Response deleteById(@PathParam("id") final long id) throws HttpException {

        final Client client = clientService.getAuthenticatedUser();

        if (client == null || client.getRole() != ClientRole.ROLE_ADMIN) {
            throw new Http403Exception();
        }

        us.delete(id);
        return Response.noContent().build();
    }

    private void validateUniversity(final UniversityDTO universityDTO) throws ValidationException {

        if (universityDTO.getName() == null || universityDTO.getName().isEmpty()) {
            throw new ValidationException(25, "Name can't be empty", "name");
        }
    }
}
