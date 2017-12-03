package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.TokenHandler;
import ar.edu.itba.paw.config.WebAuthConfig;
import ar.edu.itba.paw.controllers.exceptions.Http403Exception;
import ar.edu.itba.paw.controllers.exceptions.Http404Exception;
import ar.edu.itba.paw.dtos.*;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.models.builders.ClientBuilder;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/v1/clients")
@Component
public class ClientController {

    private final ClientService cs;
    private final DocumentService ds;
    private final ReviewService rs;
    private final ProgramService programService;

    private final PasswordEncoder passwordEncoder;

    private final TokenHandler tokenHandler;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public ClientController(final ClientService cs, final DocumentService ds, final ReviewService rs, ProgramService programService, PasswordEncoder passwordEncoder, TokenHandler tokenHandler) {
        this.cs = cs;
        this.ds = ds;
        this.rs = rs;
        this.programService = programService;
        this.passwordEncoder = passwordEncoder;
        this.tokenHandler = tokenHandler;
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public Response authenticateUser(LoginObjectDTO loginObject) {
        String username = loginObject.getUsername();
        String password = loginObject.getPassword();
        
        final Client client = cs.findByUsername(username);

        // FIXME check this
        System.out.println("MIRA ACA ** " + (client == null) + " -- " + username + " -- " + password);

        if (client == null || !passwordEncoder.isPasswordValid(client.getPassword(), password, WebAuthConfig.SECRET)) {
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
        return Response.ok(new DocumentListDTO(documents)).build();
    }

    @GET
    @Path("/me/reviews")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listReviews() throws HttpException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        final List<Review> reviews = rs.findByUserId(client.getClientId());
        return Response.ok(new ReviewListDTO(reviews)).build();
    }

    @POST
    @Path("/me/change_password")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response changePassword(final ExpandedClientDTO clientDTO) throws HttpException, ValidationException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null) {
            throw new Http403Exception();
        }

        validatePassword(clientDTO);

        if (passwordEncoder.isPasswordValid(client.getPassword(), clientDTO.getPassword(), WebAuthConfig.SECRET)) {
            throw new ValidationException(1, "The password can't match your current one!", "password");
        }

        final String encodedPassword = passwordEncoder.encodePassword(clientDTO.getPassword(), WebAuthConfig.SECRET);

        cs.update(
                client.getClientId(),
                new ClientBuilder()
                        .setName(client.getName())
                        .setEmail(client.getEmail())
                        .setPassword(encodedPassword)
                        .setRole(client.getRole())
                        .setRecoveryQuestion(client.getRecoveryQuestion())
                        .setSecretAnswer(client.getSecretAnswer())
                        .setProgram(client.getProgram())
                        .createModel()
        );

        return Response.ok(new ClientDTO(cs.findById(client.getClientId()))).build();
    }

    @POST
    @Path("/reset_password")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response resetPassword(final ExpandedClientDTO clientDTO) throws HttpException, ValidationException {

        if (clientDTO.getName() == null || clientDTO.getName().isEmpty()) {
            throw new ValidationException(1, "Invalid username", "username");
        }

        Client client = cs.findByUsername(clientDTO.getName());

        if (client == null) {
            throw new ValidationException(1, "Invalid username", "username");
        }

        if (!client.getSecretAnswer().equals(clientDTO.getSecretAnswer())) {
            throw new ValidationException(2, "Invalid answer", "secretAnswer");
        }

        validatePassword(clientDTO);

        if (passwordEncoder.isPasswordValid(client.getPassword(), clientDTO.getPassword(), WebAuthConfig.SECRET)) {
            throw new ValidationException(4, "The password can't match your current one!", "password");
        }

        final String encodedPassword = passwordEncoder.encodePassword(clientDTO.getPassword(), WebAuthConfig.SECRET);

        cs.update(
                client.getClientId(),
                new ClientBuilder()
                        .setName(client.getName())
                        .setEmail(client.getEmail())
                        .setPassword(encodedPassword)
                        .setRole(client.getRole())
                        .setRecoveryQuestion(client.getRecoveryQuestion())
                        .setSecretAnswer(client.getSecretAnswer())
                        .setProgram(client.getProgram())
                        .createModel()
        );

        return Response.ok(new ClientDTO(cs.findById(client.getClientId()))).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) throws HttpException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null || !client.isAdmin()) {
            throw new Http403Exception();
        }

        final Client requestedClient = cs.findById(id);
        if (requestedClient != null) {
            return Response.ok(new ClientDTO(requestedClient)).build();
        } else {
            throw new Http404Exception("Client not found");
        }
    }

    @POST
    @Path("/{id}")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response updateClientRole(@PathParam("id") final long id,
                                     final ClientDTO clientDTO) throws HttpException, ValidationException {

        Client client = cs.getAuthenticatedUser();

        if (client == null || !client.isAdmin()) {
            throw new Http403Exception();
        }

        client = cs.findById(id);

        cs.update(
                id,
                new ClientBuilder()
                        .setName(client.getName())
                        .setEmail(client.getEmail())
                        .setPassword(client.getPassword())
                        .setRole(clientDTO.getRole())
                        .setRecoveryQuestion(client.getRecoveryQuestion())
                        .setSecretAnswer(client.getSecretAnswer())
                        .setProgram(client.getProgram())
                        .createModel()
        );

        return Response.ok(new ClientDTO(cs.findById(id))).build();
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listClients() throws HttpException {

        final Client client = cs.getAuthenticatedUser();

        if (client == null || !client.isAdmin()) {
            throw new Http403Exception();
        }

        List<Client> clients = cs.getAll();

        return Response.ok(new ClientListDTO(clients)).build();
    }

    @POST
    @Path("/register")
    @Consumes(value = {MediaType.APPLICATION_JSON,})
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response create(@Valid final ExpandedClientDTO clientDTO) throws HttpException, ValidationException {

        final Client client = cs.getAuthenticatedUser();

        if (client != null) {
            throw new Http403Exception();
        }

        if (cs.findByUsername(clientDTO.getName()) != null) {
            throw new ValidationException(1, "Username already exists", "username");
        }

        if (cs.findByEmail(clientDTO.getEmail()) != null) {
            throw new ValidationException(2, "Email already exists", "email");
        }

        validateClient(clientDTO);
        validatePassword(clientDTO);

        final String encodedPassword = passwordEncoder.encodePassword(clientDTO.getPassword(), WebAuthConfig.SECRET);

        final Program program = programService.findById(clientDTO.getProgramId());

        if (program == null || program.getUniversity().getUniversityId() != clientDTO.getUniversityId()) {
            throw new ValidationException(1, "Program not found", "programId");
        }

        final Client newClient = cs.create(
                new ClientBuilder()
                        .setName(clientDTO.getName())
                        .setEmail(clientDTO.getEmail())
                        .setPassword(encodedPassword)
                        .setRole(ClientRole.ROLE_USER)
                        .setProgram(program)
                        .setUniversity(program.getUniversity())
                        .createModel()
        );

        String token = tokenHandler.createTokenForUser(newClient.getName());

        return Response.ok(new AuthenticationTokenDTO(token)).build();
    }

    private void validatePassword(final ExpandedClientDTO clientDTO) throws ValidationException {
        if (clientDTO.getPassword() == null || clientDTO.getPassword().isEmpty()) {
            throw new ValidationException(3, "The new password can't be empty!", "password");
        }
    }

    private void validateClient(final ClientDTO clientDTO) throws ValidationException {

        if (clientDTO.getName() == null || clientDTO.getName().isEmpty()) {
            throw new ValidationException(1, "Name can't be empty", "name");
        }

        if (clientDTO.getEmail() == null || clientDTO.getEmail().isEmpty()) {
            throw new ValidationException(1, "Email can't be empty", "email");
        }

        if (!EmailValidator.getInstance().isValid(clientDTO.getEmail())) {
            throw new ValidationException(1, "Email is invalid", "email");
        }
    }
}
