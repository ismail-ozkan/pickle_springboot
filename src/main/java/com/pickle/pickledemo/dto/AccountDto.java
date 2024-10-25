package com.pickle.pickledemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Integer id;
    private String title;
    private String email;
    private Date createdDate;
    private Integer ownerUserId;
    private String ownerUserEmail;
    private String accountImageUrl;

    // Admin bir seller kullanıcı için account oluşturur
    public AccountDto(String title, String email, String ownerUserEmail) {
        this.title = title;
        this.email = email;
        this.ownerUserEmail = ownerUserEmail;
    }
}
