package com.pickle.pickledemo.dto;

import lombok.*;

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
