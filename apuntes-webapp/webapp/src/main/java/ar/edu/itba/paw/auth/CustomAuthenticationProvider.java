package ar.edu.itba.paw.auth;

import ar.edu.itba.paw.config.WebAuthConfig;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ClientService cs;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(ClientService cs, PasswordEncoder passwordEncoder) {
        this.cs = cs;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Client client = cs.findByUsername(username);

        if (client == null || !client.getName().equals(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!passwordEncoder.isPasswordValid(client.getPassword(), password, WebAuthConfig.SECRET)) {
            throw new BadCredentialsException("Wrong password.");
        }

        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (client.getRole() == ClientRole.ROLE_ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new UsernamePasswordAuthenticationToken(client, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0);
    }

}