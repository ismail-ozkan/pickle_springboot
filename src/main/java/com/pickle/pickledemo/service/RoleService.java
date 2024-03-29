package com.pickle.pickledemo.service;

import com.pickle.pickledemo.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(int id);

    Role save(Role Roles);

    void deleteById(int id);


}
