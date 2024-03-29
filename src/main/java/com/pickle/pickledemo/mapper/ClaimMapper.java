package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.ClaimDto;
import com.pickle.pickledemo.entity.Claim;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClaimMapper {


    private final ModelMapper mapper;

    public ClaimMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Claim convertToEntity(ClaimDto dto) {
        return mapper.map(dto,Claim.class);
    }

    public ClaimDto convertToDto(Claim claim) {
        return mapper.map(claim,ClaimDto.class);
    }
}
