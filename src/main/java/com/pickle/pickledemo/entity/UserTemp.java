package com.pickle.pickledemo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "password", length = 68, nullable = false)
    @NotNull(message = "is required")
    private String password = "pass12";

    @Column(name = "first_name")
    @NotNull(message = "is required")
    private String firstName = "firstName";

    @Column(name = "last_name")
    @NotNull(message = "is required")
    private String lastName = "lastName";

    //@Column(name = "email")//opitonal
    @NotNull(message = "is required")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "must be a valid email address")
    private String email;


    public UserTemp( String password, String firstName, String lastName, String email) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}