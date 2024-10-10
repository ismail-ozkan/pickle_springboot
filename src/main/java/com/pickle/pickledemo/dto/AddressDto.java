package com.pickle.pickledemo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private int id;

    private String city;

    private String fullAddress;

}
