package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.repository.RoleRepository;
import com.pickle.pickledemo.entity.Role;
import com.pickle.pickledemo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository rolesRepository;

    public RoleServiceImpl(RoleRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Role> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Role findById(int id) {
        Optional<Role> result = rolesRepository.findById(id);
        Role roles = null;
        if (result.isPresent()) {
            roles = result.get();
        } else {
            throw new RuntimeException("Couldn't find role id - " + id);
        }
        return roles;
    }

    @Override
    public Role save(Role roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void deleteById(int id) {
        rolesRepository.deleteById(id);
    }

}
