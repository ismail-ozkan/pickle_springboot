package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Pickle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PickleRepository extends JpaRepository<Pickle, Integer> {

    List<Pickle> findAllByIsActive(boolean isActive);

    List<Pickle> findAllBySellerId(Integer sellerId);

}
