package com.pickle.pickledemo.pojos;

import lombok.*;

import javax.persistence.*;

public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer code;

    private String email;

    public Register(Integer code, String email) {
        this.code = code;
        this.email = email;
    }

}
