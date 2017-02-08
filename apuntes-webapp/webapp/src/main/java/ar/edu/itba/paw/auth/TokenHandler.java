package ar.edu.itba.paw.auth;

import ar.edu.itba.paw.config.WebAuthConfig;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class TokenHandler {

    private ClientService clientService;

    @Autowired
    public TokenHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    public Client parseUserFromToken(String token) {
        String username = null;
        try {
            username = Jwts.parser()
                    .setSigningKey(WebAuthConfig.SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            // JJWT framework does not handle this exceptions
            return null;
        }
        return clientService.findByUsername(username);
    }

    public String createTokenForUser(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, WebAuthConfig.SECRET)
                .compact();
    }

}