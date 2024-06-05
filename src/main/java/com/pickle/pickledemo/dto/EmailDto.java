package com.pickle.pickledemo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailDto {

    private String to;
    private String subject;
    private String text;

}
