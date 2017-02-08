package ar.edu.itba.paw.auth;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserAuthentication implements Authentication {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthentication.class);

    private Client client;
    private boolean authenticated = true;
    private List<SimpleGrantedAuthority> authorities;

    public UserAuthentication(Client client) {
        this.client = client;

        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (client.getRole() == ClientRole.ROLE_ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return client.getName();
    }

    @Override
    public Object getDetails() {
        return client.getName();
    }

    @Override
    public Object getPrincipal() {
        return client.getName();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return client.getName();
    }
}