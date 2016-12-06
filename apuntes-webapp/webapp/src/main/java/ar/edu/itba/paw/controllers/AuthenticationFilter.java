package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.Secured;
import ar.edu.itba.paw.dtos.ErrorMessageDTO;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.AuthenticationToken;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
@Component
public class AuthenticationFilter implements ContainerRequestFilter {


    private final ClientService cs;

    @Autowired
    public AuthenticationFilter(ClientService cs) {
        this.cs = cs;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        final AuthenticationToken authenticationToken = cs.findByToken(token);

        if (authenticationToken == null) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new ErrorMessageDTO(Response.Status.UNAUTHORIZED))
                            .build());
        }

        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {

                return new Principal() {

                    @Override
                    public String getName() {
                        return authenticationToken.getClient().getName();
                    }
                };
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });
    }

}