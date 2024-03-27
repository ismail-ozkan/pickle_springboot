package com.pickle.pickledemo.service;

import com.pickle.pickledemo.entity.Claims;

import java.util.List;

public interface ClaimsService {

    List<Claims> findAll();

    Claims findById(int id);

    Claims save(Claims Claims);

    void deleteById(int id);

}
