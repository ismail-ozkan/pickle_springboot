package com.pickle.pickledemo.external_api;

import com.pickle.pickledemo.dto.CityDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    private final ModelMapper mapper;

    public CityMapper() {
        this.mapper = new ModelMapper();
        // Özelleştirilmiş mapping tanımı
        mapper.typeMap(CityExternal.class, CityDto.class).addMappings(mapper ->
                mapper.map(CityExternal::getTitleStr, CityDto::setName)
        );
    }

    public CityExternal convertToExternal(CityDto dto) {
        return mapper.map(dto, CityExternal.class);
    }


    public CityDto convertToDto(CityExternal externalCity) {
        return mapper.map(externalCity, CityDto.class);
    }
}
