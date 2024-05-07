package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Integer> {


}
