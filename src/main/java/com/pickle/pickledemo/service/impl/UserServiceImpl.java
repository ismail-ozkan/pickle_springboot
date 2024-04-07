package com.pickle.pickledemo.service.impl;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Role;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.exceptions.UserNotFoundException;
import com.pickle.pickledemo.mapper.UserMapper;
import com.pickle.pickledemo.repository.RoleRepository;
import com.pickle.pickledemo.repository.UserRepository;
import com.pickle.pickledemo.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

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
        validation(userDto.getFirstName());
        dbUser.setFirstName(userDto.getFirstName());
        validation(userDto.getLastName());
        dbUser.setLastName(userDto.getLastName());
        validation(userDto.getAge());
        dbUser.setAge(userDto.getAge());
        validation(userDto.getEmail());
        dbUser.setEmail(userDto.getEmail());
        //if (userDto.getRole()!=null) {dbUser.setRole(userDto.getRole());}
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
    public void deleteById(Integer id) {
        if (!userRepository.getAllIds().contains(id)) {
            throw new UserNotFoundException("Id not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<Long> getAllIds() {
        return userRepository.getAllIds();
    }

    @Override
    public Address getAddressById(int id) {
        System.out.println(userRepository.getUserAddress(id));
        return (Address) userRepository.getUserAddress(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
