package com.pickle.pickledemo.rest;

import com.pickle.pickledemo.entity.Claims;
import com.pickle.pickledemo.service.ClaimsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClaimsRestController {

    private ClaimsService claimsService;

    public ClaimsRestController(ClaimsService claimsService) {
        this.claimsService = claimsService;
    }

    // define @PostConstruct to load the claims data ... only once!! like static methods, we can think
    // Veritabanı bağlantısının başlatılması, Cache'in başlatılması, Gerekli başlangıç ayarlarının yapılması:, Servislerin başlatılması gibi işlemler için kullanılabilir
    @PostConstruct
    public void initializeSomething() {
        // we can create static data to load db first. But I use spring.jpa.hibernate.ddl-auto=update, so I have already some data
    }

    @GetMapping("/claims")
    public List<Claims> getClaims() {
        List<Claims> claimsList = claimsService.findAll();
        return claimsList;
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/claims/{claimId}")
    public Claims getClaimsById(@PathVariable int claimId) {
        return claimsService.findById(claimId);
    }

    @PostMapping("/claims")
    public Claims createClaim(@RequestBody Claims claim) {
        claim.setId(0);
        return claimsService.save(claim);
    }

    @PutMapping("/claims")
    public Claims updateUser(@RequestBody Claims claim) {
        Claims dbClaim = claimsService.findById(claim.getId());
        if (dbClaim==null) {
            throw new RuntimeException("Id not found");
        }
        return claimsService.save(claim);
    }

    @DeleteMapping("/claims/{claimId}")
    public String deleteUser(@PathVariable int claimId) {
        claimsService.deleteById(claimId);
        return "Claim with " + claimId + " was deleted.";
    }


}