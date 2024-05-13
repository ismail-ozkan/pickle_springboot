package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.config.security.JwtService;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.service.PickleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PickleController {

    private final PickleService pickleService;
    private final JwtService jwtService;

    public PickleController(PickleService pickleService, JwtService jwtService) {
        this.pickleService = pickleService;
        this.jwtService = jwtService;
    }

    @GetMapping("/pickle")
    public List<Pickle> getPickles() {
        List<Pickle> pickleList = pickleService.findAll();
        return pickleList;
    }
    @GetMapping("/pickle/SellerPickle/{sellerId}")
    public ResponseEntity<List<Pickle>> getSellerPickles(@RequestHeader("Authorization") String token, @PathVariable(value = "sellerId", required = false) Integer sellerId) {
        sellerId = sellerId == 0 ? null : sellerId;
        return ResponseEntity.ok(pickleService.findSellerPickle(token,sellerId));
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/pickle/{pickleId}")
    public Pickle getPicklesById(@PathVariable Integer pickleId) {
        return pickleService.findById(pickleId);
    }

    @PostMapping("/pickle")
    public Pickle createPickle(@RequestHeader("Authorization") String token, @RequestBody PickleDto pickleDto) {
        pickleDto.setSellerId(jwtService.extractUserId(token));
        return pickleService.save(pickleDto);
    }

    @PutMapping("/pickle")
    public Pickle updateUser(@RequestBody PickleDto pickleDto) {
        Pickle dbPickle = pickleService.findById(pickleDto.getId());
        if (dbPickle==null) {
            throw new RuntimeException("Id not found");
        }
        return pickleService.save(pickleDto);
    }

    @DeleteMapping("/pickle/{pickleId}")
    public String deleteUser(@PathVariable int pickleId) {
        pickleService.deleteById(pickleId);
        return "Pickle with " + pickleId + " was deleted.";
    }


}
