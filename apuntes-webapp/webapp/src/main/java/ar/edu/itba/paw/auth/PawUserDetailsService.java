package ar.edu.itba.paw.auth;

import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class PawUserDetailsService implements UserDetailsService {

    private final ClientService cs;

    @Autowired
    public PawUserDetailsService(ClientService cs) {
        this.cs = cs;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Client client = cs.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException("No	user by	the	name " + username);
        }

        //FIXME
        final Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
        return new UserPrincipal(client, authorities);
    }
}