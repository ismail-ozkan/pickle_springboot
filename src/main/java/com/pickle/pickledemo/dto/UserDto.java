package com.pickle.pickledemo.dto;

import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {


    private int id;

    private String userName;

    private String password;

    private boolean enabled;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private Date createdDate;

    private Address address;

    private Collection<Role> roles;

    public UserDto(int id, String userName, String password, boolean enabled) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public UserDto(int id, String firstName, String lastName, Collection<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
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

    public UserDto(int id, Collection<Role> roles) {
        this.id = id;
        this.roles = roles;
    }

    //For registration


    public UserDto(String userName, String password, String firstName, String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + password + '\'' + ", enabled=" + enabled + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", age=" + age + ", createdDate=" + createdDate + ", address=" + address + ", roles=" + roles + '}';
    }
}
