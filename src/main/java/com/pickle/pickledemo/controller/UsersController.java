package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.config.ApiResponse;
import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.dto.responses.UpdatePickleResponse;
import com.pickle.pickledemo.dto.responses.UserResponse;
import com.pickle.pickledemo.entity.*;
import com.pickle.pickledemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostConstruct
    public void initializeSomething() {
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers(@AuthenticationPrincipal User user) {
        List<UserResponse> usersList = userService.findAllUsers();
        return ApiResponse.success(usersList, "Users retrieved successfully");
    }

    @GetMapping("/users/self")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@AuthenticationPrincipal User user) {
        return ApiResponse.success(userService.findById(user.getId()), "User retrieved successfully");
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable int userId) {
        return ApiResponse.success(userService.findById(userId), "User retrieved successfully");
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        user.setId(0);
        return ApiResponse.success(userService.save(user), "User created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@RequestBody UserDto user) {
        return ApiResponse.success(userService.update(user), "User updated successfully");
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable int userId) {
        userService.deleteById(userId);
        return ApiResponse.success("User deleted successfully", "User with ID " + userId + " was deleted");
    }

    @GetMapping({"/users/address/{userId}"})
    public ResponseEntity<ApiResponse<Address>> getUserAddress(@PathVariable int userId) {
        return ApiResponse.success(userService.getAddressById(userId), "User address retrieved successfully");
    }

    @PostMapping("/users/register")
    public ResponseEntity<ApiResponse<Register>> registerUser(@RequestBody UserTemp userTemp) {
        Register register = userService.saveTemp(userTemp);
        return ApiResponse.success(register, "User registration initiated successfully");
    }

    @PostMapping("/users/validate")
    public ResponseEntity<ApiResponse<UserResponse>> validateUser(@RequestBody Register register) {
        return ApiResponse.success(userService.validateSave(register), "User validated successfully", HttpStatus.CREATED);
    }

    @PostMapping("/users/resendVerification")
    public ResponseEntity<ApiResponse<Register>> resendVerification(@RequestBody Register register) {
        return ApiResponse.success(userService.resendVerification(register), "Verification code has been resent to your email");
    }

    @PutMapping("/users/favorites/pickles")
    public ResponseEntity<ApiResponse<UpdatePickleResponse>> updateFavoritePickle(@AuthenticationPrincipal User user, @RequestParam Integer pickleId) {
        return ApiResponse.success(userService.updateFavoritePickle(user.getId(), pickleId), "Favorite pickle updated successfully");
    }

    @GetMapping("/users/favorites/pickles")
    public ResponseEntity<ApiResponse<List<Pickle>>> getFavoritePickles(@AuthenticationPrincipal User user) {
        return ApiResponse.success(userService.favoritePickles(user.getId()), "Favorite pickles retrieved successfully");
    }
}
