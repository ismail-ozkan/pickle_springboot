package com.pickle.pickledemo.service;

import com.pickle.pickledemo.dao.UsersRepository;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Users;
import com.pickle.pickledemo.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public Users save(Users Users) {
        return null;
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
