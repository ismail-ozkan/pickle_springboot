package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.*;
import com.pickle.pickledemo.service.UserService;
import com.pickle.pickledemo.service.impl.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;
    private final JwtService jwtService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    // define @PostConstruct to load the users data ... only once!! like static methods, we can think
    // Veritabanı bağlantısının başlatılması, Cache'in başlatılması, Gerekli başlangıç ayarlarının yapılması:, Servislerin başlatılması gibi işlemler için kullanılabilir
    @PostConstruct
    public void initializeSomething() {
        // we can create static data to load db first. But I use spring.jpa.hibernate.ddl-auto=update, so I have already some data
    }

    @GetMapping("/users")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        List<User> usersList = userService.findAll().stream().peek(p -> p.setPassword("####")).collect(Collectors.toList());
        return ResponseEntity.ok(usersList);
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    // bu method implemetasyonu değşimeli
    // sisteme Seller Kaydetmede kullanılacak - belki sonra Employee ve Customer
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new UserDto());
        } else {
            user.setId(0);
            return ResponseEntity.ok(userService.save(user));
        }
    }

    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok("User with ID " + userId + " was deleted.");
    }

    @GetMapping({"/users/address/{userId}"})
    public ResponseEntity<Address> getUserAddress(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getAddressById(userId));
    }

    //Register user endpoint
    @PostMapping("/users/register")
    public ResponseEntity<Register> registerUser(@RequestBody UserTemp userTemp) {
        Register register = userService.saveTemp(userTemp);
        return ResponseEntity.ok(register);
    }

    //Register-2 user endpoint
    @PostMapping("/users/validate")
    public ResponseEntity<User> validateUser(@RequestBody Register register) {
        return ResponseEntity.ok(userService.validateSave(register));
    }

    // User favorites a pickle
    @PostMapping("/users/favoritePickles")
    public ResponseEntity<String> addFavoritePickle(@RequestHeader("Authorization") String token, @RequestBody PickleDto pickle) {
        Pickle addFavoritePickle = userService.addFavoritePickle(token, pickle);
        return ResponseEntity.ok(addFavoritePickle.getName() + " is added to favorites");
    }



    // List of user favorite pickles
    @GetMapping("/users/favoritePickles")
    public ResponseEntity<Set<Pickle>> getFavoritePickles(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.favoritePickles(jwtService.extractUserId(token)));
    }

}
