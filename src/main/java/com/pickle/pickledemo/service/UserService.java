package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User findById(Integer id);

    UserDto save(User users);

    // dto-entity mapping first implementation
    UserDto update(UserDto user);

    void deleteById(Integer id);

    List<Long> getAllIds();

    Address getAddressById(int id);

    // for security configuration
    User findByUserName(String userName);

}
