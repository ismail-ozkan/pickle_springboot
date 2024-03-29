package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {


}
