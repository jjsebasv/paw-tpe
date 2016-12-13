package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.TokenHandler;
import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.*;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.builders.ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

@Path("/api/v1/client")
@Component
public class ClientController {

    private final ClientService cs;
    private final DocumentService ds;
    private final ReviewService rs;

    private final TokenHandler tokenHandler;

    @Autowired
    public ClientController(final ClientService cs, final DocumentService ds, final ReviewService rs, TokenHandler tokenHandler) {
        this.cs = cs;
        this.ds = ds;
        this.rs = rs;
        this.tokenHandler = tokenHandler;
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {


        final Client client = cs.findByUsername(username);

        if (client == null || !client.getPassword().equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorMessageDTO(Response.Status.UNAUTHORIZED))
                    .build();
        }

        String token = tokenHandler.createTokenForUser(client.getName());

        return Response.ok(new AuthenticationTokenDTO(token)).build();
    }


    @GET
    @Path("/me")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response me() throws Http403Exception {

        final Client client = cs.getAuthenticatedUser();

        if (client != null) {
            return Response.ok(new ClientDTO(client)).build();
        } else {
            throw new Http403Exception();
        }
    }

    @GET
    @Path("/me/documents")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listDocuments() throws HttpException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        final List<Document> documents = ds.findByClientId(client.getClientId());
        if (documents != null) {
            return Response.ok(new DocumentListDTO(documents)).build();
        } else {
            throw new Http404Exception("Course not found");
        }
    }

    @GET
    @Path("/me/reviews")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listReviews() throws Http404Exception {

        final Client client = cs.getAuthenticatedUser();

        final List<Review> reviews = rs.findByUserId(client.getClientId());
        if (reviews != null) {
            return Response.ok(new ReviewListDTO(reviews)).build();
        } else {
            throw new Http404Exception("Course not found");
        }
    }

    @POST
    @Path("/change_password")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response changePassword(final ClientDTO clientDTO) {

        final Client client = cs.getAuthenticatedUser();

        cs.update(
                client.getClientId(),
                new ClientBuilder()
                        .setName(client.getName())
                        .setEmail(client.getEmail())
                        .setPassword(clientDTO.getPassword())
                        .setRole(client.getRole())
                        .createModel()
        );

        return Response.ok(new ClientDTO(cs.findById(client.getClientId()))).build();
    }

}
