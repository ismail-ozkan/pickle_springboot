package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.AccountDto;
import com.pickle.pickledemo.entity.Account;
import com.pickle.pickledemo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pickle.pickledemo.config.CustomResponseEntity.ok;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accountsList = accountService.findAll();
        return ok(accountsList);
    }

    // Admin bir seller kullanıcı için account oluşturur
    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@RequestBody Account account) {
        return ok(accountService.save(account));
    }

}
