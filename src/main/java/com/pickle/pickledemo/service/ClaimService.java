package com.pickle.pickledemo.service;

import com.pickle.pickledemo.entity.Claim;

import java.util.List;

public interface ClaimService {

    List<Claim> findAll();

    Claim findById(int id);

    Claim save(Claim Claims);

    void deleteById(int id);

}
