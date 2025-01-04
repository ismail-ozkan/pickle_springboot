package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.config.ApiResponse;
import com.pickle.pickledemo.dto.AccountDto;
import com.pickle.pickledemo.entity.Account;
import com.pickle.pickledemo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<ApiResponse<List<Account>>> getAccounts() {
        List<Account> accountsList = accountService.findAll();
        return ApiResponse.success(accountsList, "Accounts retrieved successfully");
    }

    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<AccountDto>> createAccount(@RequestBody Account account) {
        return ApiResponse.success(accountService.save(account), "Account created successfully", HttpStatus.CREATED);
    }
}
