package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.config.ApiResponse;
import com.pickle.pickledemo.dto.LoginResponseDto;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class LoginController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody User user) {
        LoginResponseDto response = authenticationServiceImpl.authenticate(user);
        return ApiResponse.success(response, "Login successful!");
    }
}
