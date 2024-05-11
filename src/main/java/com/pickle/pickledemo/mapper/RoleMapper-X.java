/*package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.RoleDto;
import com.pickle.pickledemo.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {


    private final ModelMapper mapper;

    public RoleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Role convertToEntity(RoleDto dto) {
        return mapper.map(dto,Role.class);
    }

    public RoleDto convertToDto(Role role) {
        return mapper.map(role,RoleDto.class);
    }
}
*/