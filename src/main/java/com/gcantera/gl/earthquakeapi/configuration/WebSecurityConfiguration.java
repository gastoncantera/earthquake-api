package com.gcantera.gl.earthquakeapi.configuration;

import com.gcantera.gl.earthquakeapi.configuration.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // configure AuthenticationManager -> UserDetailsService
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /**
         * We are implementing a REST API and need stateless authentication
         * with a JWT token; therefore, we need to set the following options:
         *
         * - Enable CORS and disable CSRF.
         * - Set session management to stateless.
         * - Set unauthorized requests exception handler.
         * - Set permissions on endpoints.
         * - Add JWT token filter.
         */

        // Enable CORS and disable CSRF. TODO: Enable CORS
        httpSecurity.csrf().disable();

        // Set session management to stateless.
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Set unauthorized requests exception handler
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        // Set permissions on endpoints
        httpSecurity.authorizeRequests()
                // Our public endpoints
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-ui/**", "/swagger-resources/**").permitAll() // springfox-swagger
                // Our private endpoints
                .anyRequest().authenticated();

        // Add JWT token filter
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        /**
         * Please note that we added the JwtTokenFilter before the Spring Security internal UsernamePasswordAuthenticationFilter.
         * We’re doing this because we need access to the user identity at this point to perform authentication/authorization,
         * and its extraction happens inside the JWT token filter based on the provided JWT token
         */
    }
}
