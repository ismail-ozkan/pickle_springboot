package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Pickle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PickleRepository extends JpaRepository<Pickle, Integer> {

}
