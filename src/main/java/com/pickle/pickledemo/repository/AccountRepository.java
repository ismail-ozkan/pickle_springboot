package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Integer> {

}
