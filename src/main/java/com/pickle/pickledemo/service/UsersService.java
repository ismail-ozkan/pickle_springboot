package com.pickle.pickledemo.service;

import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Users;

import java.util.List;

public interface UsersService {

    List<Users> findAll();

    Users findById(int id);

    Users save(Users Users);

    void deleteById(int id);

    List<Integer> getAllIds();

    Address getAddressById(int id);

}
