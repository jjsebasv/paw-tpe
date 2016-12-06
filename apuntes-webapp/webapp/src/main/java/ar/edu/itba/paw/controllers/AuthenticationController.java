package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.dtos.AuthenticationTokenDTO;
import ar.edu.itba.paw.dtos.ErrorMessageDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.AuthenticationToken;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/auth/token")
@Controller
public class AuthenticationController {

    private final ClientService cs;

    @Autowired
    public AuthenticationController(ClientService cs) {
        this.cs = cs;
    }

    @POST
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

        AuthenticationToken token = cs.findTokenFor(client);

        if (token != null) {
            cs.invalidateToken(token);
        }

        token = cs.generateToken(client);

        // Return the token on the response
        return Response.ok(new AuthenticationTokenDTO(token)).build();
    }

}
