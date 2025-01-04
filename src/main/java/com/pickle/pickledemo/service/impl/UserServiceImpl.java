package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.dto.responses.UpdatePickleResponse;
import com.pickle.pickledemo.dto.responses.UserResponse;
import com.pickle.pickledemo.entity.*;
import com.pickle.pickledemo.exceptions.user.UserNotFoundException;
import com.pickle.pickledemo.mapper.UserMapper;
import com.pickle.pickledemo.repository.PickleRepository;
import com.pickle.pickledemo.repository.RegisterRepository;
import com.pickle.pickledemo.repository.UserRepository;
import com.pickle.pickledemo.repository.UserTempRepository;
import com.pickle.pickledemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTempRepository userTempRepository;
    private final RegisterRepository registerRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final PickleRepository pickleRepository;
    @Qualifier("applicationTaskExecutor")
    private final TaskExecutor taskExecutor;


    public List<UserResponse> findAllUsers() {
        return userRepository.findAllWithAccount().stream()
                .map(userMapper::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Integer id) {
        User user = userRepository.findByIdWithAccount(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Didn't find users id - " + id));
        return userMapper.convertToResponse(user);
    }

    @Override
    public UserResponse save(User user) {
        // if a user is already registered with the same email address then throw exception
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.convertToResponse(userRepository.save(user));
    }

    @Override//TODO test edilmedi
    public UserResponse update(UserDto user) {
        User dbUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Didn't find users id - " + user.getId()));
        updateUserData(dbUser,user);
        return userMapper.convertToResponse(dbUser);
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
        if (!byEmail.isPresent()) {
            throw new UserNotFoundException("Didn't find users email - " + email);
        }
        return byEmail.get();
    }

    @Override
    public Register saveTemp(UserTemp user) {
        // if a user is already registered with the same email address then throw exception
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userTempRepository.save(user);
        Integer otp = generateRandomNumber(4);
        taskExecutor.execute(() -> emailService.sendOtpMail(user.getEmail(), otp));
        //emailService.sendOtpMail(user.getEmail(), otp);
        Register register = new Register(otp, user.getEmail());
        return registerRepository.save(register);
    }
    @Override
    public UserResponse validateSave(Register register) {
        // if a user is already registered with the same email address then throw exception
        if (userRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        Register dbRegister = registerRepository.findFirstByEmailOrderByIdDesc(register.getEmail());
        if (!register.getCode().equals(dbRegister.getCode())) {
            throw new RuntimeException("Registration is not valid - ");
        }
        UserTemp userTemp = userTempRepository.findFirstByEmailOrderByIdDesc(dbRegister.getEmail());
        User user = userMapper.convertToUserTemp(userTemp);
        // We give Customer role to the User as default
        user.setRole(Role.ROLE_CUSTOMER);
        user.setId(0);
        return userMapper.convertToResponse(userRepository.save(user));
    }

    @Override
    public List<Pickle> favoritePickles(Integer userId) {
        return userRepository.findById(userId).get().getFavoritePickles();
    }

    @Override
    public UpdatePickleResponse updateFavoritePickle(Integer userId, Integer pickleId) {
        Pickle pickle = pickleRepository.findById(pickleId)
                .orElseThrow(() -> new RuntimeException("Pickle not found with id " + pickleId));

        User user = userRepository.findById(userId).orElseThrow();

        boolean isFavorite = user.getFavoritePickles().contains(pickle);
        user.getFavoritePickles().removeIf(p -> p.getId().equals(pickleId));

        if (!isFavorite) {
            user.getFavoritePickles().add(pickle);
        }

        userRepository.save(user); // This will update the join table automatically
        return new UpdatePickleResponse(!isFavorite, pickleId);


        /*Pickle pickle = pickleRepository.findById(pickleDto.getId())
                .orElseThrow(() -> new RuntimeException("Pickle not found with id " + pickleDto.getId()));

        Integer userId = jwtService.extractUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        List<Pickle> favoritePickles = Optional.ofNullable(user.getFavoritePickles())
                .orElseGet(() -> {
                    user.setFavoritePickles(new ArrayList<>());
                    return user.getFavoritePickles();
                });

        if (!favoritePickles.contains(pickle)) {
            favoritePickles.add(pickle);

            List<User> usersWhoFavorited = Optional.ofNullable(pickle.getUsersWhoFavorited())
                    .orElseGet(() -> {
                        pickle.setUsersWhoFavorited(new ArrayList<>());
                        return pickle.getUsersWhoFavorited();
                    });

            if (!usersWhoFavorited.contains(user)) {
                usersWhoFavorited.add(user);
            }

            userRepository.save(user);
            pickleRepository.save(pickle);
        }

        return pickle;
        ----------------------------------------------------------------
        Integer pickleId = pickleDto.getId();
        Optional<Pickle> pickleOptional = pickleRepository.findById(pickleId);

        if (pickleOptional.isPresent()) {
            Integer userId = jwtService.extractUserId(token);
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Pickle pickle = pickleOptional.get();

                List<Pickle> favoritePickles = user.getFavoritePickles();
                if (favoritePickles == null) {
                    favoritePickles = new ArrayList<>();
                }

                // Kullanıcının favori turşularına turşu ekle
                if (!favoritePickles.contains(pickle)) {
                    favoritePickles.add(pickle);
                    user.setFavoritePickles(favoritePickles);

                    // Turşunun favori kullanıcılarına kullanıcı ekle
                    List<User> usersWhoFavorited = pickle.getUsersWhoFavorited();
                    if (usersWhoFavorited == null) {
                        usersWhoFavorited = new ArrayList<>();
                    }

                    if (!usersWhoFavorited.contains(user)) {
                        usersWhoFavorited.add(user);
                        pickle.setUsersWhoFavorited(usersWhoFavorited);
                    }

                    userRepository.save(user);
                    pickleRepository.save(pickle);
                }

                return pickle;
            } else {
                throw new RuntimeException("User not found with id " + userId);
            }
        } else {
            throw new RuntimeException("Pickles not found with id " + pickleId);
        }
        if (pickleRepository.findById(pickleDto.getId()).isPresent()) {
            List<Pickle> pickleSet = null;
            Optional<User> userDb = userRepository.findById(jwtService.extractUserId(token));
            Optional<Pickle> pickleDb = pickleRepository.findById(pickleDto.getId());
            User user = userDb.get();
            Pickle pickle = pickleDb.get();
            pickleSet = user.getFavoritePickles();
            pickleSet.add(pickle);
            user.setFavoritePickles(pickleSet);
            userRepository.save(user);
            pickleRepository.save(pickle);
            return pickle;
        } else {
            throw new RuntimeException("Pickles not found with id " + pickleDto.getId());
        }*/
    }

    public int generateRandomNumber(int numberOfDigits) {
        Random random = new Random();
        int min = (int) Math.pow(10, numberOfDigits - 1);
        int max = (int) Math.pow(10, numberOfDigits) - 1;
        return min + random.nextInt(max - min + 1);
    }
}
