package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Register;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.entity.UserTemp;
import com.pickle.pickledemo.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    // define @PostConstruct to load the users data ... only once!! like static methods, we can think
    // Veritabanı bağlantısının başlatılması, Cache'in başlatılması, Gerekli başlangıç ayarlarının yapılması:, Servislerin başlatılması gibi işlemler için kullanılabilir
    @PostConstruct
    public void initializeSomething() {
        // we can create static data to load db first. But I use spring.jpa.hibernate.ddl-auto=update, so I have already some data
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> usersList = userService.findAll().stream().map(p -> {
            p.setPassword("####");
            return p;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(usersList);
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/users/{userId}")
    public User getUsersById(@PathVariable int userId) {
        return userService.findById(userId);
    }

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
    public UserDto updateUser(@RequestBody UserDto user) {
       /* User dbUser = userService.findById(user.getId());
        System.out.println(dbUser);
        if (dbUser==null) {
            throw new UserNotFoundException("Id not found");
        }
        dbUser = userService.save(user);
        return dbUser;*/
        return userService.update(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        userService.deleteById(userId);
        return "User with " + userId + " was deleted.";
    }

    @GetMapping({"/users/address/{userId}"})
    public Address getUserAddress(@PathVariable int userId) {
        return userService.getAddressById(userId);
    }

    // Kullanıcıya role tanımlaması sadece admin tarafından yapılması
    @PutMapping("/users/roles")
    public UserDto giveRoleToUser(@RequestBody UserDto userRq) {
        return userService.giveRoleToUser(userRq);
    }

    //Register user endpoint
    @PostMapping("/users/register")
    public ResponseEntity<Register> registerUser(@RequestBody UserTemp userTemp) {
        Register register = userService.saveTemp(userTemp);
        return ResponseEntity.ok(register);
    }
    //Register-2 user endpoint
    @PostMapping("/users/validate")
    public ResponseEntity<User> registerUser(@RequestBody Register register) {
        return ResponseEntity.ok(userService.validateSave(register));

    }


}
