package ar.edu.itba.paw.config;

import ar.edu.itba.paw.auth.StatelessAuthenticationFilter;
import ar.edu.itba.paw.auth.TokenAuthenticationService;
import ar.edu.itba.paw.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {


    public static final String SECRET = "SECRET_CODE";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private ClientService cs;

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().permitAll()
                .and().addFilterBefore(new StatelessAuthenticationFilter(new TokenAuthenticationService(cs)), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and().csrf().disable();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/favicon.ico", "/403");
    }
}
