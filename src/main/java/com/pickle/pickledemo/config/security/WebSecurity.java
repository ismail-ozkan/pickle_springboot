package com.pickle.pickledemo.config.security;

import com.pickle.pickledemo.service.impl.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurity {

  private final JWTService jwtService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
    http.cors().and().csrf().disable()
        .authorizeHttpRequests()
        .anyRequest().permitAll()
        .and()
        .addFilter(new JWTAuthorizationFilter(authenticationManager, jwtService))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.headers().frameOptions().disable();
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();//.applyPermitDefaultValues();

    // İzin verilen origin olarak Netlify URL'inizi ekleyin
    corsConfiguration.setAllowedOriginPatterns(List.of(
            "*",
            "https://*.netlify.app",
            "http://*.netlify.app",
            "https://pickle-web-app.netlify.app"
    ));

    // İzin verilen HTTP metodları
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));

    // İzin verilen başlıklar
    corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

    // Kimlik bilgilerini (credentials) içerecek şekilde ayarlayın
    corsConfiguration.setAllowCredentials(true);

    // CORS yapılandırmasını endpointlere uygulayın
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
