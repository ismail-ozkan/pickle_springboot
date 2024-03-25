package com.pickle.pickledemo.dao;

import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    /*void save(Users user);

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

    String getUserAddressById(Integer userId);*/
  /*  List<String> sortByName();

    List<String> findInclude(String str);

    void dropTable();

    String getUserAddressById(Integer userId);*/


    
    @Query(value = "select id from Users", nativeQuery = true)
    List<Integer> getAllIds();

    @Query(value = "select a from Address a where a.id = (select u.address.id from Users u where u.id = :userId)")
    Address getUserAddress(@Param("userId") int userId);


}
