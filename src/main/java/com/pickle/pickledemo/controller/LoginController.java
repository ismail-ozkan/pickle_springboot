package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.LoginResponseDto;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.service.impl.AuthenticationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:63343")
@RequestMapping(value = "/api")
public class LoginController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    public LoginController(AuthenticationServiceImpl service) {
        this.authenticationServiceImpl = service;
    }

    @CrossOrigin(origins = "http://localhost:63343")
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> login(@RequestBody User user) {
        return ResponseEntity.ok(authenticationServiceImpl.authenticate(user));
    }

}
