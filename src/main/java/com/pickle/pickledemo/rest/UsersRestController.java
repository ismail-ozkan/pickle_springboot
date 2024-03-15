package com.pickle.pickledemo.rest;

import com.pickle.pickledemo.dao.UsersDAO;
import com.pickle.pickledemo.entity.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private final UsersDAO usersDAO;

    public UsersRestController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    // define @PostConstruct to load the users data ... only once!! like static methods, we can think
    // Veritabanı bağlantısının başlatılması, Cache'in başlatılması, Gerekli başlangıç ayarlarının yapılması:, Servislerin başlatılması gibi işlemler için kullanılabilir
    @PostConstruct
    public void initializeSomething() {
        // we can create static data to load db first. But I use spring.jpa.hibernate.ddl-auto=update, so I have already some data
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        List<Users> usersList = usersDAO.findAll();
        return usersList;
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/users/{id}")
    public Users getUsersById(@PathVariable int id) {
       return usersDAO.findById(id);
    }





}
