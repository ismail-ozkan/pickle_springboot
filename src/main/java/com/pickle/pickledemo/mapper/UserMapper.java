package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.UserDto;
import com.pickle.pickledemo.dto.responses.AccountResponse;
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
        mapper.getConfiguration()
                .setPropertyCondition(context -> context.getSource() != null);
    }

    public UserResponse convertToResponse(User user) {
        if (user == null) return null;

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEnabled(user.isEnabled());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setAge(user.getAge());
        response.setCreatedDate(user.getCreatedDate());
        response.setAddress(user.getAddress());
        response.setRole(user.getRole());
        response.setFavoritePickles(user.getFavoritePickles());

        if (user.getAccount() != null) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setId(user.getAccount().getId());
            accountResponse.setTitle(user.getAccount().getTitle());
            accountResponse.setEmail(user.getAccount().getEmail());
            accountResponse.setOwnerUserId(user.getAccount().getOwnerUserId());
            accountResponse.setOwnerUserEmail(user.getAccount().getOwnerUserEmail());
            accountResponse.setAccountImageUrl(user.getAccount().getAccountImageUrl());
            response.setAccount(accountResponse);
        }

        return response;
    }
    public User convertToEntity(UserDto userDto) {
        return mapper.map(userDto,User.class);
    }
    public User convertToUserTemp(UserTemp userTemp) {
        return mapper.map(userTemp,User.class);
    }

    public UserDto convertToDto(User user) {
        return mapper.map(user, UserDto.class);
    }
    public UserResponse convertToResponse(UserDto user) {
        return mapper.map(user, UserResponse.class);
    }
}
