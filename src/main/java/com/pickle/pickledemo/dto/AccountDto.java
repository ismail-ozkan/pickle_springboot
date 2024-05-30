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
public class AccountDto {

    private Integer id;
    private String title;
    private String email;
    private Date createdDate;
    private Integer ownerUserId;
    private String ownerUserEmail;

    // Admin bir seller kullanıcı için account oluşturur
    public AccountDto(String title, String email, String ownerUserEmail) {
        this.title = title;
        this.email = email;
        this.ownerUserEmail = ownerUserEmail;
    }
}
