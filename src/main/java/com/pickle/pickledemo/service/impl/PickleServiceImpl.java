package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.PickleCustomerDto;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.*;
import com.pickle.pickledemo.mapper.PickleMapper;
import com.pickle.pickledemo.repository.AccountRepository;
import com.pickle.pickledemo.repository.FileRepository;
import com.pickle.pickledemo.repository.PickleRepository;
import com.pickle.pickledemo.repository.UserRepository;
import com.pickle.pickledemo.service.PickleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PickleServiceImpl implements PickleService {

    private final PickleRepository pickleRepository;
    private final PickleMapper pickleMapper;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final FileRepository fileRepository;


    @Override
    public List<Pickle> findAll(User user) {
        if (user.getRole().equals(Role.ROLE_ADMIN)) {
            return pickleRepository.findAll();
        } else {
            Integer accountId = accountRepository.findAccountByOwnerUserId(user.getId()).getId();
            return pickleRepository.findAllBySellerId(accountId);
        }
    }

    @Override
    public List<Pickle> findAllBySellerId(Integer sellerId) {
        return pickleRepository.findAllBySellerId(sellerId);
    }

    @Override
    public List<PickleCustomerDto> findAllForCustomer() {
        return pickleRepository.findAllByIsActive(true)
                .stream()
                .map(pickleMapper::convertToPickleForCustomerDto)
                .collect(Collectors.toList());
    }

    /*@Override
    public List<Pickle> findSellerPickle(Integer sellerId) {
        if (sellerId == null) {
            Integer userId = jwtService.decode(token).getId();
            return pickleRepository.findAll().stream().filter(p -> p.getSellerId().equals(userId)).collect(Collectors.toList());
        }
        return pickleRepository.findAll().stream().filter(p -> p.getSellerId().equals(sellerId)).collect(Collectors.toList());


    }*/

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
    public Pickle save(PickleDto pickleDto, User user) {
        if (user.getRole().equals(Role.ROLE_ADMIN)) {
            pickleDto.setSellerId(pickleDto.getSellerId());
        } else {
            pickleDto.setSellerId(accountRepository.findAccountByOwnerUserId(user.getId()).getId());
        }
        pickleDto.setPrice((int) (pickleDto.getCost() * 1.2));
        File file = fileRepository.findById(pickleDto.getFileId()).orElseThrow();
        pickleDto.setFile(file);
        return pickleRepository.save(pickleMapper.convertToEntity(pickleDto));
    }

    @Override
    public void deleteById(int id) {
        pickleRepository.deleteById(id);
    }
}
