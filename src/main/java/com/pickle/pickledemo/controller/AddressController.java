package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.config.ApiResponse;
import com.pickle.pickledemo.dto.CityDto;
import com.pickle.pickledemo.external_api.GetExternalCity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AddressController {

    private final GetExternalCity getExternalCity;

    @GetMapping("/cities")
    public ResponseEntity<ApiResponse<List<CityDto>>> cityList() {
        return ApiResponse.success(getExternalCity.getExternalCityList(), "Cities retrieved successfully");
    }
}
