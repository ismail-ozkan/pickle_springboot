package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.ClaimDto;
import com.pickle.pickledemo.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {


    private final ModelMapper mapper;

    public AddressMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Address convertToEntity(ClaimDto dto) {
        return mapper.map(dto,Address.class);
    }

    public ClaimDto convertToDto(Address address) {
        return mapper.map(address,ClaimDto.class);
    }
}
