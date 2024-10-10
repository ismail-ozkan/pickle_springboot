package com.pickle.pickledemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickleCustomerDto {

    private Integer id;

    private String name;

    private Integer price;

    private Integer sellerId;

    private Date createdDate;
}
