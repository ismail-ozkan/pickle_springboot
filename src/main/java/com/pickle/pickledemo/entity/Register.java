package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "register")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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