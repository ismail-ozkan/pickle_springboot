package com.pickle.pickledemo.dto;

import com.pickle.pickledemo.entity.Account;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    private String password;

    private boolean enabled;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private Date createdDate;

    private Address address;

    private Account account;

    private Role role;

    private List<Pickle> favoritePickles;

    public UserDto(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserDto(int id, String firstName, String lastName, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserDto(String firstName, String lastName, String email, int age, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    public UserDto(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UserDto(int id, String firstName, String lastName, String email, int age, Date createdDate, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.createdDate = createdDate;
        this.address = address;
    }


    public UserDto( String password, String firstName, String lastName, String email) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Admin Seller kayıt
    public UserDto(String password, String firstName, String lastName, String email, Role role) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" + "id=" + id +
                ", userName='" +  + '\'' +
                ", password='" + password +
                '\'' + ", enabled=" + enabled +
                ", firstName='" + firstName +
                '\'' + ", lastName='" + lastName +
                '\'' + ", email='" + email + '\'' +
                ", age=" + age +
                ", createdDate=" + createdDate +
                ", address=" + address +
                ", role=" + role + '}';
    }
}
