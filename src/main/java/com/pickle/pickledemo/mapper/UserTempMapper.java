package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.entity.Register;
import com.pickle.pickledemo.entity.UserTemp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserTempMapper {


    private final ModelMapper mapper;

    public UserTempMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Register convertToEntity(UserTemp userDto) {
        return mapper.map(userDto,Register.class);
    }

}
