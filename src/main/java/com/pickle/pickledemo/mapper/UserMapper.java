package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.UserDto;
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

    public UserDto convertToDto(User user) {

        UserDto mapDto = mapper.map(user, UserDto.class);
        mapDto.setPassword("###");
        return mapDto;
    }
}
