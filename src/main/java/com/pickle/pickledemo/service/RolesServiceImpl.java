package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dao.RolesRepository;
import com.pickle.pickledemo.entity.Roles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    private RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles findById(int id) {
        Optional<Roles> result = rolesRepository.findById(id);
        Roles roles = null;
        if (result.isPresent()) {
            roles = result.get();
        } else {
            throw new RuntimeException("Couldn't find role id - " + id);
        }
        return roles;
    }

    @Override
    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void deleteById(int id) {
        rolesRepository.deleteById(id);
    }

}
