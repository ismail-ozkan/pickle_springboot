package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegisterRepository extends JpaRepository<Register, Integer> {


    @Query(value = "select r from Register r where r.email=:email")
    Register findByEmail(@Param("email") String email);


}
