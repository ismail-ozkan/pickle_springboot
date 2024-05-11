package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    ADMIN,
    SELLER,
    EMPLOYEE,
    CUSTOMER;

    private List<Claim> claims;
}
