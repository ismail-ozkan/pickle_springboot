package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.config.ApiResponse;
import com.pickle.pickledemo.dto.PickleCustomerDto;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.service.PickleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PickleController {

    private final PickleService pickleService;

    @GetMapping("/pickle")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public ResponseEntity<ApiResponse<List<Pickle>>> getPickles(@AuthenticationPrincipal User user) {
        List<Pickle> pickleList = pickleService.findAll(user);
        return ApiResponse.success(pickleList, "Pickles retrieved successfully");
    }

    @GetMapping("/pickle/list")
    public ResponseEntity<ApiResponse<List<PickleCustomerDto>>> getPicklesForCustomer() {
        List<PickleCustomerDto> pickleList = pickleService.findAllForCustomer();
        return ApiResponse.success(pickleList, "Customer pickles retrieved successfully");
    }

    @GetMapping("/pickle/{sellerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<List<Pickle>>> getSellerPickles(@PathVariable(value = "sellerId", required = false) Integer sellerId) {
        return ApiResponse.success(pickleService.findAllBySellerId(sellerId), "Seller pickles retrieved successfully");
    }

    @GetMapping("/pickle/list/{pickleId}")
    public ResponseEntity<ApiResponse<Pickle>> getPicklesById(@PathVariable Integer pickleId) {
        return ApiResponse.success(pickleService.findById(pickleId), "Pickle retrieved successfully");
    }

    @PostMapping("/pickle")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public ResponseEntity<ApiResponse<Pickle>> createPickle(@AuthenticationPrincipal User user, @RequestBody PickleDto pickleDto) {
        return ApiResponse.success(pickleService.save(pickleDto, user), "Pickle created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/pickle")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public ResponseEntity<ApiResponse<Pickle>> updatePickle(@RequestBody PickleDto pickleDto) {
        return ApiResponse.success(pickleService.updatePickle(pickleDto), "Pickle updated successfully");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @DeleteMapping("/pickle/{pickleId}")
    public ResponseEntity<ApiResponse<String>> deletePickle(@PathVariable int pickleId) {
        pickleService.deleteById(pickleId);
        return ApiResponse.success("Pickle deleted successfully", "Pickle with ID " + pickleId + " was deleted");
    }
}
