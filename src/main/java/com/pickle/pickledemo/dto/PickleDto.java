package com.pickle.pickledemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pickle.pickledemo.entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    private Integer cost;

    private Date createdDate;

    // it should be true as default
    @Value("${default.active:true}")
    private Boolean isActive = true;

    private Integer fileId;

    private File file;;

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
