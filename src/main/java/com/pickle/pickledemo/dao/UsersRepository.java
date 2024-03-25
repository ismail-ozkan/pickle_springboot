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


    @Query(value = "select id from Users", nativeQuery = true)
    List<Integer> getAllIds();

    @Query(value = "select a from Address a where a.id = (select u.address.id from Users u where u.id = :userId)")
    Address getUserAddress(@Param("userId") int userId);


}
