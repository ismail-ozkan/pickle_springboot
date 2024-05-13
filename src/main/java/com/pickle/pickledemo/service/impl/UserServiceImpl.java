package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.*;
import com.pickle.pickledemo.exceptions.user.UserNotFoundException;
import com.pickle.pickledemo.mapper.UserMapper;
import com.pickle.pickledemo.mapper.UserTempMapper;
import com.pickle.pickledemo.repository.RegisterRepository;
import com.pickle.pickledemo.repository.UserRepository;
import com.pickle.pickledemo.repository.UserTempRepository;
import com.pickle.pickledemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTempRepository userTempRepository;
    private final RegisterRepository registerRepository;
    private final UserMapper userMapper;
    private final UserTempMapper userTempMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> result = userRepository.findById(id);
        User user = null;
        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new UserNotFoundException("Didn't find users id - " + id);
        }
        user.setPassword("###");
        return user;
    }

    @Override
    public UserDto save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.convertToDto(userRepository.save(user));
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

        dbUser.setFirstName(userDto.getFirstName());

        dbUser.setLastName(userDto.getLastName());

        dbUser.setAge(userDto.getAge());

        dbUser.setEmail(userDto.getEmail());
        if (userDto.getAddress()!=null) {dbUser.setAddress(userDto.getAddress());}
    }

    @Override
    public void deleteById(Integer id) {
        System.out.println("userRepository.getAllIds() = " + userRepository.getAllIds());
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

    @Override
    public User findByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new UserNotFoundException("Didn't find users email - " + email);
        }
        return byEmail.get();
    }

    @Override
    public Register saveTemp(UserTemp user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userTempRepository.save(user);
        Register register = new Register(generateRandomNumber(4), user.getEmail());
        return registerRepository.save(register);
    }
    @Override
    public User validateSave(Register register) {
        Register dbRegister = registerRepository.findByEmail(register.getEmail());
        if (!register.getCode().equals(dbRegister.getCode())) {
            throw new RuntimeException("Registration is not valid - ");
        }
        UserTemp userTemp = userTempRepository.findByEmail(dbRegister.getEmail());
        User user = userMapper.convertToUserTemp(userTemp);
        user.setRole(Role.SELLER);
        return userRepository.save(user);
    }

    public int generateRandomNumber(int numberOfDigits) {
        Random random = new Random();
        int min = (int) Math.pow(10, numberOfDigits - 1);
        int max = (int) Math.pow(10, numberOfDigits) - 1;
        return min + random.nextInt(max - min + 1);
    }
}
