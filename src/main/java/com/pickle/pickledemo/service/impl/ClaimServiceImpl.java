package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.repository.ClaimRepository;
import com.pickle.pickledemo.entity.Claim;
import com.pickle.pickledemo.service.ClaimService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClaimServiceImpl implements ClaimService {

    private ClaimRepository claimsRepository;

    public ClaimServiceImpl(ClaimRepository claimsRepository) {
        this.claimsRepository = claimsRepository;
    }

    @Override
    public List<Claim> findAll() {
        return claimsRepository.findAll();
    }

    @Override
    public Claim findById(int id) {
        Optional<Claim> result = claimsRepository.findById(id);
        Claim claims = null;
        if (result.isPresent()) {
            claims = result.get();
        } else {
            throw new RuntimeException("Didn't find claims id - " + id);
        }
        return claims;
    }

    @Override
    public Claim save(Claim claim) {
        return claimsRepository.save(claim);
    }

    @Override
    public void deleteById(int id) {
        claimsRepository.deleteById(id);
    }
}
