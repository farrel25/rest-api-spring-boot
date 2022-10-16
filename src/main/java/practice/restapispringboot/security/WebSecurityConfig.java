package practice.restapispringboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import practice.restapispringboot.services.AppUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * There are 2 methods of WebSecurityConfigurerAdapter that need
     * to be overridden. The first method is used to give permission
     * which endpoints can be accessed without logging in first.
     * 
     * Then the second method, there will be a configuration to tell
     * Spring Security related to the authentication manager builder.
     * So we need to tell Spring Security about the implementation
     * of the UserDetailsService in the AppUserService and also the
     * encoder we are using.
     */

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * .authorizeRequests().antMatchers("/api/users/register").permitAll()
     * We give anyone access to the endpoint "/api/users/register"
     * 
     * .anyRequest().fullyAuthenticated()
     * .and().httpBasic();
     * All request endpoints other than the request endpoints that we have
     * allowed in antMatchers(), must be fully authenticated and by
     * implementing the httpBasic method.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests().antMatchers("/api/users/register").permitAll()
        .anyRequest().fullyAuthenticated()
        .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);

        return provider;
    }
}
