package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dto.AccountDto;
import com.pickle.pickledemo.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(int id);

    AccountDto save(Account account);

    void deleteById(int id);
}
