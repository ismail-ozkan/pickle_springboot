package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.PickleCustomerDto;
import com.pickle.pickledemo.service.impl.JwtService;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.service.PickleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PickleController {

    private final PickleService pickleService;
    private final JwtService jwtService;

    @GetMapping("/pickle")
    public ResponseEntity<List<Pickle>> getPickles() {
        List<Pickle> pickleList = pickleService.findAll();
        return ResponseEntity.ok(pickleList);
    }

    @GetMapping("/pickle/list")
    public ResponseEntity<List<PickleCustomerDto>> getPicklesForCustomer() {
        List<PickleCustomerDto> pickleList = pickleService.findAllForCustomer();
        return ResponseEntity.ok(pickleList);
    }
    @GetMapping("/pickle/SellerPickle/{sellerId}")
    public ResponseEntity<List<Pickle>> getSellerPickles(@RequestHeader("Authorization") String token, @PathVariable(value = "sellerId", required = false) Integer sellerId) {
        sellerId = sellerId == 0 ? null : sellerId;
        return ResponseEntity.ok(pickleService.findSellerPickle(token,sellerId));
    }

    // @PathVariable should have the same name in the method signature
    @GetMapping("/pickle/{pickleId}")
    public ResponseEntity<Pickle> getPicklesById(@PathVariable Integer pickleId) {
        return ResponseEntity.ok(pickleService.findById(pickleId));
    }

    @PostMapping("/pickle")
    public ResponseEntity<Pickle> createPickle(@RequestHeader("Authorization") String token, @RequestBody PickleDto pickleDto) {
        pickleDto.setSellerId(jwtService.extractUserId(token));
        return ResponseEntity.ok(pickleService.save(pickleDto));
    }

    @PutMapping("/pickle")
    public ResponseEntity<Pickle> updateUser(@RequestBody PickleDto pickleDto) {
        Pickle dbPickle = pickleService.findById(pickleDto.getId());
        if (dbPickle==null) {
            throw new RuntimeException("Id not found");
        }
        return ResponseEntity.ok(pickleService.save(pickleDto));
    }

    @DeleteMapping("/pickle/{pickleId}")
    public ResponseEntity<String> deleteUser(@PathVariable int pickleId) {
        pickleService.deleteById(pickleId);
        return ResponseEntity.ok("Pickle with " + pickleId + " was deleted.");
    }


}