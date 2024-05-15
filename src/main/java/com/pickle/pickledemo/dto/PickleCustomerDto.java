package com.pickle.pickledemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PickleCustomerDto {

    private Integer id;

    private String name;

    private Integer price;

    private Integer sellerId;

    private Date createdDate;
}
