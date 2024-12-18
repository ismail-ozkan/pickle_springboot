package com.pickle.pickledemo.dto.responses;

import com.pickle.pickledemo.entity.Account;
import com.pickle.pickledemo.entity.Address;
import com.pickle.pickledemo.entity.Pickle;
import com.pickle.pickledemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private int id;

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

}
