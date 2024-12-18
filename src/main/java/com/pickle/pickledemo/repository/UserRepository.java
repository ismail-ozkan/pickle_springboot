package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "select id from Users", nativeQuery = true)
    List<Integer> getAllIds();

    @Query(value = "select a from Address a where a.id = (select u.address.id from User u where u.id = :userId)")
    Address getUserAddress(@Param("userId") int userId);

    Optional<User> findByEmail(String username);

    // update a user favorite pickles with pickleId
    @Query(value = "UPDATE User u SET u.favoritePickles = :pickles WHERE u.id = :userId", nativeQuery = true)
    void updateFavoritePickles(@Param("userId") int userId, @Param("pickles") List<Pickle> pickles);

}
//@Query(value = "select u from User u where u.userName=:uName and u.enabled=true")
//User findByUserName(@Param("uName") String uName);