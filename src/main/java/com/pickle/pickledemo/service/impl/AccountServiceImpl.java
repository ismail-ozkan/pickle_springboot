package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.AccountDto;
import com.pickle.pickledemo.entity.Account;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.mapper.AccountMapper;
import com.pickle.pickledemo.repository.AccountRepository;
import com.pickle.pickledemo.service.AccountService;
import com.pickle.pickledemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final JwtService jwtService;
    private final UserService userService;


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }


    @Override
    public Account findById(int id) {
        Optional<Account> result = accountRepository.findById(id);
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
        } else {
            throw new RuntimeException("Didn't find account id - " + id);
        }
        return account;
    }

    @Override
    public AccountDto save(Account account) {
        User ownerUser = userService.findByEmail(account.getOwnerUserEmail());
        account.setOwnerUserId(ownerUser.getId());
        Account savedAccount = accountRepository.save(account);
        ownerUser.setAccount(account);
        userService.save(ownerUser);
        return accountMapper.convertToDto(savedAccount);
    }

    @Override
    public void deleteById(int id) {
        accountRepository.deleteById(id);
    }
}
