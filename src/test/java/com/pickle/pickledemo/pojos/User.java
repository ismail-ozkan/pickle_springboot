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

    @JsonIgnoreProperties
    private Integer id;
    private String password;
    private Boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
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

