package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.UserTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTempRepository extends JpaRepository<UserTemp, Integer> {

    UserTemp findFirstByEmailOrderByIdDesc(String email);

    @Query(value = "select u from UserTemp u where u.email=:email")
    UserTemp findByEmail(@Param("email") String email);

}
