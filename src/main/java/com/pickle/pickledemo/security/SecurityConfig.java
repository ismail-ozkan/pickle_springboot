package com.pickle.pickledemo.security;

import com.pickle.pickledemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //beans
    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .antMatchers(HttpMethod.PUT, "/api/users/roles").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/roles").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/roles/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/roles").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/roles/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/api/roles/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/claims").hasAnyRole("MANAGER","ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/claims").hasRole("EMPLOYEE")
                        .antMatchers(HttpMethod.DELETE, "/api/claims/**").hasRole("ADMIN")

        );
        // use HTTP Basic authentication
        http.httpBasic();

        // disable Cross Site Request Forgery (CSRF)
        // in general not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf().disable();

        return http.build();
    }

}
