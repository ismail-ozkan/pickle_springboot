package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;

import java.util.List;

public interface PickleService {

    List<Pickle> findAll();

    Pickle findById(int id);

    Pickle save(PickleDto pickleDto);

    void deleteById(int id);
    List<Pickle> findSellerPickle(String token, Integer sellerId);

}
