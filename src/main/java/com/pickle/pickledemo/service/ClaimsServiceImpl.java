package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dao.ClaimsRepository;
import com.pickle.pickledemo.entity.Claims;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClaimsServiceImpl implements ClaimsService {

    private ClaimsRepository claimsRepository;

    public ClaimsServiceImpl(ClaimsRepository claimsRepository) {
        this.claimsRepository = claimsRepository;
    }

    @Override
    public List<Claims> findAll() {
        return claimsRepository.findAll();
    }

    @Override
    public Claims findById(int id) {
        Optional<Claims> result = claimsRepository.findById(id);
        Claims claims = null;
        if (result.isPresent()) {
            claims = result.get();
        } else {
            throw new RuntimeException("Didn't find claims id - " + id);
        }
        return claims;
    }

    @Override
    public Claims save(Claims claim) {
        return claimsRepository.save(claim);
    }

    @Override
    public void deleteById(int id) {
        claimsRepository.deleteById(id);
    }
}
