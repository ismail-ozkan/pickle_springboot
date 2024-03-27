package com.pickle.pickledemo.rest;

import com.pickle.pickledemo.entity.Roles;
import com.pickle.pickledemo.exceptions.UserNotFoundException;
import com.pickle.pickledemo.service.RolesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RolesRestController {

    private RolesService rolesService;

    public RolesRestController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    // define @PostConstruct to load the roles data ... only once!! like static methods, we can think
    // Veritabanı bağlantısının başlatılması, Cache'in başlatılması, Gerekli başlangıç ayarlarının yapılması:, Servislerin başlatılması gibi işlemler için kullanılabilir
    @PostConstruct
    public void initializeSomething() {
        // we can create static data to load db first. But I use spring.jpa.hibernate.ddl-auto=update, so I have already some data
    }

    @GetMapping("/roles")
    public List<Roles> getRoles() {
        List<Roles> rolesList = rolesService.findAll();
        return rolesList;
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/roles/{roleId}")
    public Roles getRolesById(@PathVariable int roleId) {
        return rolesService.findById(roleId);
    }

    @PostMapping("/roles")
    public Roles createUser(@RequestBody Roles role) {
        role.setId(0);
        return rolesService.save(role);
    }

    @PutMapping("/roles")
    public Roles updateUser(@RequestBody Roles role) {
        Roles dbRole = rolesService.findById(role.getId());
        if (dbRole==null) {
            throw new UserNotFoundException("Id not found");
        }
        return rolesService.save(role);
    }

    @DeleteMapping("/roles/{roleId}")
    public String deleteRole(@PathVariable int roleId) {
        rolesService.deleteById(roleId);
        return "Role with " + roleId + " was deleted.";
    }


}
