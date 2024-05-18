package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.*;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    UserDto save(User users);

    // dto-entity mapping first implementation
    UserDto update(UserDto user);

    void deleteById(Integer id);

    List<Integer> getAllIds();

    Address getAddressById(int id);

    // for security configuration
    User findByEmail(String userName);


    Register saveTemp(UserTemp user);

    User validateSave(Register register);

    Set<Pickle> favoritePickles(Integer userId);

    Pickle addFavoritePickle(String token, PickleDto pickle);
}
