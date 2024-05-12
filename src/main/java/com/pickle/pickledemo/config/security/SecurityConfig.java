package com.pickle.pickledemo.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                .antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                .antMatchers(HttpMethod.POST,  "/api/users/validate").permitAll()
                .antMatchers(HttpMethod.POST,  "/api/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .authenticationProvider(authenticationProvider)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
/*
                .antMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/users/roles").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/users/{userId}").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/users/address/{userId}").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/claims").hasAnyRole("SELLER","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/claims").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.DELETE, "/api/claims/**").hasRole("ADMIN")*/