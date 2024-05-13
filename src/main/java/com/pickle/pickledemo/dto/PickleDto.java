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

}
