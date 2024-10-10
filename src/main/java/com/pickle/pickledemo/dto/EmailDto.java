package com.pickle.pickledemo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String to;
    private String subject;
    private String text;

}
