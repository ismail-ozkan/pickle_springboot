package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer id;

    @Column(length = 68, nullable = false)
    @NotNull(message = "is required")
    @Builder.Default
    private String password = "pass12";

    @NotNull(message = "is required")
    @Builder.Default
    private String firstName = "firstName";

    @NotNull(message = "is required")
    @Builder.Default
    private String lastName = "lastName";

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