package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClaimRepository extends JpaRepository<Claim, Integer> {

}
