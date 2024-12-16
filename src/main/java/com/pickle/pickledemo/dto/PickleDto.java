package com.pickle.pickledemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickleDto {

    private Integer id;

    private String name;

    private Integer price;

    private Integer sellerId;

    private Integer cost = 0;

    private Date createdDate;

    private Boolean isActive;

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

    // add favorites to a pickle
    public PickleDto(Integer id) {
        this.id = id;
    }
}
