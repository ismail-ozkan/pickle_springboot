package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.dto.responses.UserResponse;
import com.pickle.pickledemo.entity.User;
import com.pickle.pickledemo.entity.UserTemp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User convertToEntity(UserDto userDto) {
        return mapper.map(userDto,User.class);
    }
    public User convertToUserTemp(UserTemp userTemp) {
        return mapper.map(userTemp,User.class);
    }

    public UserResponse convertToResponse(User user) {
        return mapper.map(user, UserResponse.class);
    }
    public UserResponse convertToResponse(UserDto user) {
        return mapper.map(user, UserResponse.class);
    }
}
