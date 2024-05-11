package com.pickle.pickledemo.auth;

import com.pickle.pickledemo.config.security.JwtService;
import com.pickle.pickledemo.entity.Role;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.entity.UserTemp;
import com.pickle.pickledemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserTemp userTemp) {
        var user = User.builder()
                .firstName(userTemp.getFirstName())
                .lastName(userTemp.getLastName())
                .email(userTemp.getEmail())
                .password(passwordEncoder.encode(userTemp.getPassword()))
                .role(Role.EMPLOYEE)
                .build();
        userRepository.save(user);
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(User userRq) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRq.getEmail(), userRq.getPassword())
        );
        var user = userRepository.findByEmail(userRq.getEmail())
                .orElseThrow();
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
