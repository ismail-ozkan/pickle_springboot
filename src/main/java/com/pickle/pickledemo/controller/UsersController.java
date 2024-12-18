package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.dto.responses.UpdatePickleResponse;
import com.pickle.pickledemo.dto.responses.UserResponse;
import com.pickle.pickledemo.entity.*;
import com.pickle.pickledemo.service.UserService;
import com.pickle.pickledemo.service.impl.JWTService;
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

import static com.pickle.pickledemo.config.CustomResponseEntity.ok;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;
    private final JWTService jwtService;

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getUsers(@AuthenticationPrincipal User user) {
        List<UserResponse> usersList = userService.findAllUsers();
        return ok(usersList);
    }
    @GetMapping("/users/self")
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal User user) {
        return ok(userService.findById(user.getId()));
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int userId) {
        return ok(userService.findById(userId));
    }


    // sisteme Seller Kaydetmede kullanılacak - Sadece admin erişebilir
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        user.setId(0);
        return ok(userService.save(user));
    }

    @PutMapping("/users")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserDto user) {
        return ok(userService.update(user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        userService.deleteById(userId);
        return ok("User with ID " + userId + " was deleted.");
    }

    @GetMapping({"/users/address/{userId}"})
    public ResponseEntity<Address> getUserAddress(@PathVariable int userId) {
        return ok(userService.getAddressById(userId));
    }

    //Register user endpoint
    @PostMapping("/users/register")
    public ResponseEntity<Register> registerUser(@RequestBody UserTemp userTemp) {
        Register register = userService.saveTemp(userTemp);
        return ok(register);
    }

    //Register-2 user endpoint
    @PostMapping("/users/validate")
    public ResponseEntity<UserResponse> validateUser(@RequestBody Register register) {
        return ok(userService.validateSave(register), HttpStatus.CREATED);
    }

    // User favorites a pickle
    @PutMapping("/users/favorites/pickles")
    public ResponseEntity<UpdatePickleResponse> updateFavoritePickle(@AuthenticationPrincipal User user, @RequestParam Integer pickleId) {
        /*if (addFavoritePickle==null) {
            return ResponseEntity.badRequest().body("You already have added this pickle in your favorite list,!");
        }*/
        //return ok(addFavoritePickle.getName() + " is added to favorites");
        return ok(userService.updateFavoritePickle(user.getId(), pickleId));
    }

    // List of user favorite pickles
    @GetMapping("/users/favorites/pickles")
    public ResponseEntity<List<Pickle>> getFavoritePickles(@AuthenticationPrincipal User user) {
        return ok(userService.favoritePickles(user.getId()));
    }

}
