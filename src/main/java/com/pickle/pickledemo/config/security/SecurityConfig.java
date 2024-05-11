package com.pickle.pickledemo.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers( "/api/users/register").permitAll()
                .antMatchers( "/api/users/validate").permitAll()
                .antMatchers( "/api/authenticate").permitAll()
                /*.antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/users/roles").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/{userId}").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/users/address/{userId}").hasRole("EMPLOYEE")*/

                /*.antMatchers(HttpMethod.GET, "/api/roles").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/roles").hasRoßle("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/claims").hasAnyRole("SELLER","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/claims").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.DELETE, "/api/claims/**").hasRole("ADMIN")*/
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        /*http.authorizeHttpRequests(http ->
                http
                        .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                       //.antMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .antMatchers(HttpMethod.PUT, "/api/users").permitAll()
                        .antMatchers(HttpMethod.PUT, "/api/users/roles").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/users/{userId}").hasRole("EMPLOYEE")
                        .antMatchers(HttpMethod.GET, "/api/users/address/{userId}").hasRole("EMPLOYEE")

                        .antMatchers(HttpMethod.GET, "/api/roles").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/roles/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/roles").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/roles/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/roles/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/claims").hasAnyRole("SELLER","ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/claims").hasRole("EMPLOYEE")
                        .antMatchers(HttpMethod.DELETE, "/api/claims/**").hasRole("ADMIN")

        );*/

        return http.build();
    }

}
