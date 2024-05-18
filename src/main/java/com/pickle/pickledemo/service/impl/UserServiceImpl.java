package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.*;
import com.pickle.pickledemo.exceptions.user.UserNotFoundException;
import com.pickle.pickledemo.mapper.PickleMapper;
import com.pickle.pickledemo.mapper.UserMapper;
import com.pickle.pickledemo.mapper.UserTempMapper;
import com.pickle.pickledemo.repository.PickleRepository;
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
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTempRepository userTempRepository;
    private final RegisterRepository registerRepository;
    private final UserMapper userMapper;
    private final UserTempMapper userTempMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final PickleRepository pickleRepository;
    private final PickleMapper pickleMapper;

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
        Integer otp = generateRandomNumber(4);
        emailService.sendOtpMail(user.getEmail(), otp);
        Register register = new Register(otp, user.getEmail());
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
        // We give Customer role to the User as default
        user.setRole(Role.CUSTOMER);
        user.setId(0);
        return userRepository.save(user);
    }

    @Override
    public Set<Pickle> favoritePickles(Integer userId) {
        return userRepository.getUserFavoritePickles(userId);
    }

    @Override
    public Pickle addFavoritePickle(String token, PickleDto pickleDto) {
        if (pickleRepository.findById(pickleDto.getId()).isPresent()) {
            Optional<User> userDb = userRepository.findById(jwtService.extractUserId(token));
            Optional<Pickle> pickleDb = pickleRepository.findById(pickleDto.getId());
            User user = userDb.get();
            Pickle pickle = pickleDb.get();
            user.getFavoritePickles().add(pickle);
            userRepository.save(user);
            return pickle;
        } else {
            throw new RuntimeException("Pickles not found with id " + pickleDto.getId());
        }
    }

    public int generateRandomNumber(int numberOfDigits) {
        Random random = new Random();
        int min = (int) Math.pow(10, numberOfDigits - 1);
        int max = (int) Math.pow(10, numberOfDigits) - 1;
        return min + random.nextInt(max - min + 1);
    }
}
