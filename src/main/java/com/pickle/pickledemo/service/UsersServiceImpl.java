package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dao.UsersRepository;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Users;
import com.pickle.pickledemo.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(int id) {
        Optional<Users> result = usersRepository.findById(id);
        Users user = null;
        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new UserNotFoundException("Didn't find users id - " + id);
        }
        return user;
    }

    @Override
    public Users save(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public void deleteById(int id) {
        if (!usersRepository.getAllIds().contains(id)) {
            throw new UserNotFoundException("Id not found");
        }
        usersRepository.deleteById(id);
    }

    @Override
    public List<Integer> getAllIds() {
        return usersRepository.getAllIds();
    }

    @Override
    public Address getAddressById(int id) {
        System.out.println(usersRepository.getUserAddress(id));
        return (Address) usersRepository.getUserAddress(id);
    }
}
