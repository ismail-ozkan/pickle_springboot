package com.pickle.pickledemo.rest;

import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Users;
import com.pickle.pickledemo.exception.UserNotFoundException;
import com.pickle.pickledemo.service.UsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private UsersService usersService;

    public UsersRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    // define @PostConstruct to load the users data ... only once!! like static methods, we can think
    // Veritabanı bağlantısının başlatılması, Cache'in başlatılması, Gerekli başlangıç ayarlarının yapılması:, Servislerin başlatılması gibi işlemler için kullanılabilir
    @PostConstruct
    public void initializeSomething() {
        // we can create static data to load db first. But I use spring.jpa.hibernate.ddl-auto=update, so I have already some data
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        List<Users> usersList = usersService.findAll();
        return usersList;
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/users/{userId}")
    public Users getUsersById(@PathVariable int userId) {
        if (!usersService.findAll().contains(userId)) {
            throw new UserNotFoundException("Id not found");
        }
        return usersService.findById(userId);
    }

    @PostMapping("/users")
    public Users createUser(@RequestBody Users user) {
        usersService.save(user);
        return user;
    }

    @PutMapping("/users")
    public Users updateUser(@RequestBody Users user) {
        Users dbUser = usersService.findById(user.getId());
        if (dbUser==null) {
            throw new UserNotFoundException("Id not found");
        }
        return usersService.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        usersService.deleteById(userId);
        return "User with " + userId + " was deleted.";
    }

    @GetMapping({"/users/address/{userId}"})
    public Address getUserAddress(@PathVariable int userId) {
        return usersService.getAddressById(userId);
    }

}
