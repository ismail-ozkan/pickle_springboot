package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "select id from Users", nativeQuery = true)
    List<Long> getAllIds();

    @Query(value = "select a from Address a where a.id = (select u.address.id from User u where u.id = :userId)")
    Address getUserAddress(@Param("userId") int userId);

    @Query(value = "select u from User u where u.userName=:uName and u.enabled=true")
    User findByUserName(@Param("uName") String uName);




}
