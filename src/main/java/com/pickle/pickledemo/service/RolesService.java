package com.pickle.pickledemo.service;

import com.pickle.pickledemo.entity.Roles;

import java.util.List;

public interface RolesService {

    List<Roles> findAll();

    Roles findById(int id);

    Roles save(Roles Roles);

    void deleteById(int id);


}
