package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.exceptions.UserNotFoundException;
import com.pickle.pickledemo.mapper.UserMapper;
import com.pickle.pickledemo.repository.UserRepository;
import com.pickle.pickledemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);
        User user = null;
        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new UserNotFoundException("Didn't find users id - " + id);
        }
        return user;
    }

    @Override
    public User save(User users) {
        return userRepository.save(users);
    }

    @Override
    public UserDto update(UserDto user) {
        Optional<User> dbUser = userRepository.findById(user.getId());
        if (!dbUser.isPresent()) {
            throw new UserNotFoundException("Didn't find users id - " + user.getId());
        }
        updateUserData(dbUser.get(),user);
        return userMapper.convertToDto(dbUser.get());
    }

    private void updateUserData(User dbUser, UserDto userDto) {
        validation(userDto.getFirstName());
        dbUser.setFirstName(userDto.getFirstName());
        validation(userDto.getLastName());
        dbUser.setLastName(userDto.getLastName());
        validation(userDto.getAge());
        dbUser.setAge(userDto.getAge());
        validation(userDto.getEmail());
        dbUser.setEmail(userDto.getEmail());
        if (userDto.getRole()!=null) {dbUser.setRole(userDto.getRole());}
        if (userDto.getAddress()!=null) {dbUser.setAddress(userDto.getAddress());}
    }

    private void validation(String data) {
        if (data.isBlank() || data.isEmpty() || data==null) {
            throw new RuntimeException("Required field must be filled");
        }
    }
    private void validation(int data) {
        if (data==0 || (Integer)data==null) {
            throw new RuntimeException("Required field must be filled");
        }
    }



    @Override
    public void deleteById(int id) {
        if (!userRepository.getAllIds().contains(id)) {
            throw new UserNotFoundException("Id not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<Integer> getAllIds() {
        return userRepository.getAllIds();
    }

    @Override
    public Address getAddressById(int id) {
        System.out.println(userRepository.getUserAddress(id));
        return (Address) userRepository.getUserAddress(id);
    }
}
