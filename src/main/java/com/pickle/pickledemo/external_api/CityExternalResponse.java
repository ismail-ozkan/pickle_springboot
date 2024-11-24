package com.pickle.pickledemo.external_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityExternalResponse {

    private int statusCode;
    private Result result;


}

@Data
class Result {

    public List<CityExternal> cities;
    public String responseMessage;

}

