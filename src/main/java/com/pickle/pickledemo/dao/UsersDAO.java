package com.pickle.pickledemo.dao;

import com.pickle.pickledemo.entity.Users;

import java.util.List;

public interface UsersDAO {

    void save(Users user);

    Users findById(int id);

    List<Users> findAll();

    Users findByFirstName(String firstName);

    List<String> sortByName();

    void deleteById(Integer id);

    void updateUsers(Users updatedUser);
}
