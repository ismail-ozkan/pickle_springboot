package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.dto.responses.UserResponse;
import com.pickle.pickledemo.entity.*;

import java.util.List;

public interface UserService {

    List<UserResponse> findAllUsers();

    UserResponse findById(Integer id);

    UserResponse save(User users);

    // dto-entity mapping first implementation
    UserResponse update(UserDto user);

    void deleteById(Integer id);

    List<Integer> getAllIds();

    Address getAddressById(int id);

    // for security configuration
    User findByEmail(String userName);


    Register saveTemp(UserTemp user);

    UserResponse validateSave(Register register);

    List<Pickle> favoritePickles(Integer userId);

    Pickle addFavoritePickle(String token, PickleDto pickle);
}
