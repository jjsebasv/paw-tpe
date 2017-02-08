package ar.edu.itba.paw.auth;


import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final TokenHandler tokenHandler;

    public TokenAuthenticationService(ClientService clientService) {
        tokenHandler = new TokenHandler(clientService);
    }

    public void addAuthentication(HttpServletResponse response, Client user) {
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user.getEmail()));
    }

    Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Client user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}