package com.pickle.pickledemo.mapper;

import com.pickle.pickledemo.dto.PickleDto;
import com.pickle.pickledemo.entity.Pickle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PickleMapper {


    private final ModelMapper mapper;

    public PickleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Pickle convertToEntity(PickleDto dto) {
        return mapper.map(dto,Pickle.class);
    }

    public PickleDto convertToDto(Pickle pickle) {
        return mapper.map(pickle,PickleDto.class);
    }
}
