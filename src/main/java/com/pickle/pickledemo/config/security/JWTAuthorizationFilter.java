package com.pickle.pickledemo.config.security;

import com.pickle.pickledemo.service.impl.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  private final JWTService jwtService;
  private static final String HEADER_STRING = "Authorization";
  private static final String TOKEN_PREFIX = "Bearer ";

  public JWTAuthorizationFilter(AuthenticationManager authManager, JWTService jwtService) {
    super(authManager);
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
                                  HttpServletResponse res,
                                  FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HEADER_STRING);

    if (header != null && header.startsWith(TOKEN_PREFIX)) {
      UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      UserDetails userDetails = jwtService.decode(token.replace(TOKEN_PREFIX, ""));
      if (userDetails != null) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      }
      return null;
    }
    return null;
  }
}
