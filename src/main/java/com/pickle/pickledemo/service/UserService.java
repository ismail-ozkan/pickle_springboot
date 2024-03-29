package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User save(User users);

    // dto-entity mapping first implementation
    UserDto update(UserDto user);

    void deleteById(int id);

    List<Integer> getAllIds();

    Address getAddressById(int id);

}
