package com.pickle.pickledemo.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePickleResponse {

    private Boolean isFavorite;
    private Integer favoritePickleId;

}
