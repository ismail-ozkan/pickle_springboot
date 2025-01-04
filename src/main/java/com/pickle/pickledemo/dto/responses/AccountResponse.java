package com.pickle.pickledemo.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Integer id;
    private String title;
    private String email;
    private Integer ownerUserId;
    private String ownerUserEmail;
    private String accountImageUrl;
} 