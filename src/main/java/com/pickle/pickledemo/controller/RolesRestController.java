package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.entity.Claim;
import com.pickle.pickledemo.entity.Role;
import com.pickle.pickledemo.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RolesRestController {

    private RoleService rolesService;

    public RolesRestController(RoleService rolesService) {
        this.rolesService = rolesService;
    }

    // define @PostConstruct to load the roles data ... only once!! like static methods, we can think
    // Veritabanı bağlantısının başlatılması, Cache'in başlatılması, Gerekli başlangıç ayarlarının yapılması:, Servislerin başlatılması gibi işlemler için kullanılabilir
    @PostConstruct
    public void initializeSomething() {
        // we can create static data to load db first. But I use spring.jpa.hibernate.ddl-auto=update, so I have already some data
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        List<Role> rolesList = rolesService.findAll();
        return rolesList;
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/roles/{roleId}")
    public Role getRolesById(@PathVariable int roleId) {
        return rolesService.findById(roleId);
    }

    @PostMapping("/roles")
    public Role createUser(@RequestBody Role role) {
        role.setId(0);
        return rolesService.save(role);
    }

    @PutMapping("/roles")
    public Role updateUser(@RequestBody Role role) {
        Role dbRole = rolesService.findById(role.getId());
        if (dbRole==null) {
            throw new RuntimeException("Id not found");
        }
        return rolesService.save(role);
    }

    @DeleteMapping("/roles/{roleId}")
    public String deleteRole(@PathVariable int roleId) {
        rolesService.deleteById(roleId);
        return "Role with " + roleId + " was deleted.";
    }

    @PutMapping("/roles/addClaim/{roleId}")
    public Role addClaimToRole(@PathVariable int roleId, @RequestBody List<Claim> claims) {
        Role dbRole = rolesService.findById(roleId);
        dbRole.setRoleClaims(claims);
        return rolesService.save(dbRole);
    }
}
