package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dto.PickleCustomerDto;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.entity.User;

import java.util.List;

public interface PickleService {

    List<Pickle> findAll(User user);

    List<Pickle> findAllBySellerId(Integer sellerId);

    List<PickleCustomerDto> findAllForCustomer();

    Pickle findById(int id);

    Pickle save(PickleDto pickleDto, User user);
    //Pickle save(PickleDto pickleDto, Integer sellerId);

    void deleteById(int id);

    //List<Pickle> findSellerPickle(String token, Integer sellerId);

}
