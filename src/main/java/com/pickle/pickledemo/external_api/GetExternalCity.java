package com.pickle.pickledemo.external_api;

import com.pickle.pickledemo.dto.CityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetExternalCity {

    private final RestTemplate restTemplate;
    private final CityMapper mapper;

    public List<CityDto> getExternalCityList() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, null);
        ResponseEntity<CityExternalResponse> externalResponseEntity = restTemplate.exchange(
                "https://nevitadev.solidict.com/Test/ApiGatewayWeb/Parameter/Cities?isActive=true",
                HttpMethod.GET,
                requestEntity,
                CityExternalResponse.class);
        List<CityExternal> cities = Objects.requireNonNull(externalResponseEntity.getBody()).getResult().cities;
        return cities.stream().map(mapper::convertToDto).collect(Collectors.toList());


    }

}
