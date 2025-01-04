package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.PickleCustomerDto;
import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.File;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.entity.Role;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.mapper.PickleMapper;
import com.pickle.pickledemo.repository.AccountRepository;
import com.pickle.pickledemo.repository.FileRepository;
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

    @Override
    public Pickle updatePickle(PickleDto pickleDto) {
        // Find the existing pickle by its ID
        Pickle dbPickle = pickleRepository.findById(pickleDto.getId()).orElseThrow();

        // Update fields conditionally
        Optional.ofNullable(pickleDto.getName()).ifPresent(dbPickle::setName);
        Optional.ofNullable(pickleDto.getCost()).ifPresent(cost -> {
            dbPickle.setCost(cost);
            dbPickle.setPrice((int) (cost * 1.2));  // Update price if cost is updated
        });
        Optional.ofNullable(pickleDto.getIsActive()).ifPresent(dbPickle::setIsActive);

        // If fileId is present, update file reference
        Optional.ofNullable(pickleDto.getFileId())
                .ifPresent(fileId -> dbPickle.setFile(fileRepository.findById(fileId).orElseThrow()));

        // Save and return the updated pickle
        return pickleRepository.save(dbPickle);
    }
}
