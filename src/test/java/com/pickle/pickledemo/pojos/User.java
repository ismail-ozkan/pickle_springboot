package com.pickle.pickledemo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private Integer id;

    private String password = "pass12";


    private boolean enabled = true;

    private String firstName = "firstName";

   private String lastName = "lastName";

    private String email;

    private int age = 30;

    private Date createdDate;

    private Address address;

    private Role role;

    private List<Pickle> favoritePickles;

    // authenticate request body
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Admin add user in the system
    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}