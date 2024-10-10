package com.pickle.pickledemo.config.security;

import com.pickle.pickledemo.service.impl.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private final JWTService jwtService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
        .authorizeRequests()
        .anyRequest().permitAll()
        .and()
        .addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtService))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.headers().frameOptions().disable();
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
    corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
    corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
