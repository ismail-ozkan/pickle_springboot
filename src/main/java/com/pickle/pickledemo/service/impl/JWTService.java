package com.pickle.pickledemo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pickle.pickledemo.entity.Role;
import com.pickle.pickledemo.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String encode(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("userEmail", user.getEmail())
                .withClaim("authorities", user.getRole().name())
                //.withArrayClaim("authorities", new String[]{Role.ROLE_USER.name()})
                .withExpiresAt(new Date(System.currentTimeMillis() + 604800000))
                .sign(HMAC512(jwtSecret));
    }

    public User decode(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(HMAC512(jwtSecret))
                    .build()
                    .verify(token);
            Integer userId = Integer.valueOf(decodedJWT.getSubject());
            String userRole = decodedJWT.getClaim("authorities").asString();
            //Set<GrantedAuthority> roles = Arrays.stream(decodedJWT.getClaim("authorities").asArray(String.class)).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            String userEmail = decodedJWT.getClaim("userEmail").asString();
            return new User(userId, userEmail, Role.valueOf(userRole));
        } catch (Exception e) {
            System.err.println("Decode exception: " + e);
            return null;
        }
    }
}

