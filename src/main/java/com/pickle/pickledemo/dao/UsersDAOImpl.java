package com.pickle.pickledemo.dao;

import com.pickle.pickledemo.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    @Override
    public Users findById(int id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public List<Users> findAll() {
        TypedQuery<Users> query = entityManager.createQuery("FROM Users", Users.class);
        List<Users> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Users findByFirstName(String firstName) {
        TypedQuery<Users> query = entityManager.createQuery("FROM Users where firstName=:firstName", Users.class).setParameter("firstName", firstName);
        Users singleResult = query.getSingleResult();
        return singleResult;
    }

    @Override
    public List<String> sortByName() {
        TypedQuery<String> query = entityManager.createQuery("SELECT firstName FROM Users ORDER BY firstName DESC", String.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Users user = entityManager.find(Users.class, id);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void updateUsers(Users updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public List<String> findInclude(String str) {
        //1. way to use parameter in the query
        String queryPiece = "%" + str + "%";
        TypedQuery<String> query = entityManager.createQuery("SELECT firstName FROM Users WHERE BINARY(firstName) LIKE:queryPieceParam ", String.class);
        query.setParameter("queryPieceParam",queryPiece);

        return query.getResultList();


        //2. way to use parameter in the query

    }

    //TODO UPDATE ve DELETE methodlarıyla .executeUpdate() methodu kullanımı

    //TODO Query içerisinde Parametre kullanımı

    //TODO SQL query pratik için fikir üretip uygulanacak, örneğin ad soyad birleştrime concat() gibi vs.
}
