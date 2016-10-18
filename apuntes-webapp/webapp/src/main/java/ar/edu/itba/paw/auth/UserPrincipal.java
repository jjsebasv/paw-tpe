package ar.edu.itba.paw.auth;

import ar.edu.itba.paw.models.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class UserPrincipal extends User {

    private final Client client;


    public UserPrincipal(final Client client, Collection<? extends GrantedAuthority> authorities) {
        super(client.getName(), client.getPassword(), authorities);
        this.client = client;
    }


    public Client getClient() {
        return client;
    }

}
