package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.PickleCustomerDto;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.mapper.PickleMapper;
import com.pickle.pickledemo.repository.PickleRepository;
import com.pickle.pickledemo.service.PickleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PickleServiceImpl implements PickleService {

    private final PickleRepository pickleRepository;
    private final PickleMapper pickleMapper;
    private final JWTService jwtService;


    @Override
    public List<Pickle> findAll() {
        return pickleRepository.findAll();
    }

    @Override
    public List<PickleCustomerDto> findAllForCustomer() {
        return findAll().stream().map(p -> pickleMapper.convertToPickleForCustomerDto(p)).collect(Collectors.toList());
    }

    @Override
    public List<Pickle> findSellerPickle(String token, Integer sellerId) {
        if (sellerId == null) {
            Integer userId = jwtService.decode(token).getId();
            return pickleRepository.findAll().stream().filter(p -> p.getSellerId().equals(userId)).collect(Collectors.toList());
        }
        return pickleRepository.findAll().stream().filter(p -> p.getSellerId().equals(sellerId)).collect(Collectors.toList());


    }

    @Override
    public Pickle findById(int id) {
        Optional<Pickle> result = pickleRepository.findById(id);
        Pickle pickle = null;
        if (result.isPresent()) {
            pickle = result.get();
        } else {
            throw new RuntimeException("Didn't find pickle id - " + id);
        }
        return pickle;
    }

    @Override
    public Pickle save(PickleDto pickleDto) {
        pickleDto.setPrice((int) (pickleDto.getCost() * 1.2));
        return pickleRepository.save(pickleMapper.convertToEntity(pickleDto));
    }

    @Override
    public void deleteById(int id) {
        pickleRepository.deleteById(id);
    }
}
