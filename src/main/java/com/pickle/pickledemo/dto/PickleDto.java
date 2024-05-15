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
public class PickleDto {

    private Integer id;

    private String name;

    private Integer price;

    private Integer sellerId;

    private Integer cost = 0;

    private Date createdDate;

    public PickleDto(String name, Integer cost) {
        this.name = name;
        this.cost = cost;
    }

    public PickleDto(Integer id, String name, Integer price, Integer sellerId, Date createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        this.createdDate = createdDate;
    }
}
