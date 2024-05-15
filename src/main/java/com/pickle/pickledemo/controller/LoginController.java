package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.LoginResponseDto;
import com.pickle.pickledemo.service.impl.AuthenticationServiceImpl;
import com.pickle.pickledemo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    public LoginController(AuthenticationServiceImpl service) {
        this.authenticationServiceImpl = service;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> login(@RequestBody User user) {
        return ResponseEntity.ok(authenticationServiceImpl.authenticate(user));
    }

}
