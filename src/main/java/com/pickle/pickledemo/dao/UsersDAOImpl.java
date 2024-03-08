package com.pickle.pickledemo.dao;

import com.pickle.pickledemo.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class UsersDAOImpl implements UsersDAO{

    private EntityManager entityManager;

    public UsersDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional// coming from spingboot not jakarta
    public void save(Users user) {
        entityManager.persist(user);
    }
}
