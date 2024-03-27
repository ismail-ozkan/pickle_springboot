package com.pickle.pickledemo.dao;

import com.pickle.pickledemo.entity.Claims;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClaimsRepository extends JpaRepository<Claims, Integer> {

}
