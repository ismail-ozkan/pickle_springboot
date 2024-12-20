package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.PickleCustomerDto;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.service.PickleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pickle.pickledemo.config.CustomResponseEntity.ok;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PickleController {

    private final PickleService pickleService;

    // Admin list all pickles and Sellers lists only their pickles
    @GetMapping("/pickle")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public ResponseEntity<List<Pickle>> getPickles(@AuthenticationPrincipal User user) {
        List<Pickle> pickleList = pickleService.findAll(user);
        return ok(pickleList);
    }

    // Customers list all pickles
    @GetMapping("/pickle/list")
    public ResponseEntity<List<PickleCustomerDto>> getPicklesForCustomer() {
        List<PickleCustomerDto> pickleList = pickleService.findAllForCustomer();
        return ResponseEntity.ok(pickleList);
    }

    // Admin lists seller pickle
    @GetMapping("/pickle/{sellerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Pickle>> getSellerPickles(@PathVariable(value = "sellerId", required = false) Integer sellerId) {
        return ResponseEntity.ok(pickleService.findAllBySellerId(sellerId));
    }

    // @PathVariable should have the same name in the method signature
    // Customers list a specific pickle
    @GetMapping("/pickle/list/{pickleId}")
    public ResponseEntity<Pickle> getPicklesById(@PathVariable Integer pickleId) {
        return ResponseEntity.ok(pickleService.findById(pickleId));
    }

    // Admin and Sellers create a new Pickle
    @PostMapping("/pickle")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public ResponseEntity<Pickle> createPickle(@AuthenticationPrincipal User user, @RequestBody PickleDto pickleDto) {
        return ResponseEntity.ok(pickleService.save(pickleDto, user));
    }

    // Admin and Sellers update Pickle
    @PutMapping("/pickle")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public ResponseEntity<Pickle> updatePickle(@RequestBody PickleDto pickleDto) {
        return ResponseEntity.ok(pickleService.updatePickle(pickleDto));
    }

    // Admin and Sellers delete Pickle
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @DeleteMapping("/pickle/{pickleId}")
    public ResponseEntity<String> deletePickle(@PathVariable int pickleId) {
        pickleService.deleteById(pickleId);
        return ResponseEntity.ok("Pickle with " + pickleId + " was deleted.");
    }


}
