package com.pickle.pickledemo.dao;

import com.pickle.pickledemo.entity.Users;
import com.pickle.pickledemo.rest.exceptions.UserNotFoundException;

import java.util.List;

public interface UsersDAO {

    void save(Users user);

    Users findById(int id) throws UserNotFoundException;

    List<Users> findAll();

    Users findByFirstName(String firstName);

    List<String> sortByName();

    void deleteById(Integer id);

    void updateUsers(Users updatedUser);

    List<String> findInclude(String str);

    void updateUserById(int id, Users theUser);

    void dropTable();

    List<Integer> getAllIds();

    String getUserAddressById(Integer userId);
}
