package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.AddressDto;
import com.pickle.pickledemo.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {


    private final ModelMapper mapper;

    public AddressMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Address convertToEntity(AddressDto dto) {
        return mapper.map(dto,Address.class);
    }

    public AddressDto convertToDto(Address address) {
        return mapper.map(address,AddressDto.class);
    }
}
