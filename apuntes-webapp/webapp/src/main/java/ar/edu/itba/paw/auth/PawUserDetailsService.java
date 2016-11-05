package ar.edu.itba.paw.auth;

import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (client.getRole() == ClientRole.ROLE_ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new UserPrincipal(client, authorities);
    }
}