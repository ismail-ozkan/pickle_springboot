package com.pickle.pickledemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Data
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "password", length = 68, nullable = false)
    @NotNull(message = "is required")
    private String password = "pass12";

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "first_name")
    @NotNull(message = "is required")
    private String firstName = "firstName";

    @Column(name = "last_name")
    @NotNull(message = "is required")
    private String lastName = "lastName";

    //@Column(name = "email")//opitonal
    @NotNull(message = "is required")
    //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Z|a-z]{2,}$", message = "must be a valid email address")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "must be a valid email address")
    private String email = "email@example.com";

    @Min(value = 1, message = "age must be greater than 1")
    @Max(value = 150, message = "age must be less than 150")
    private int age = 30;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType. DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "user_favorite_pickles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pickle_id"))
    @JsonManagedReference
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password='" + password + '\'' + ", enabled=" + enabled + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", age=" + age + ", createdDate=" + createdDate + ", address=" + address + ", role=" + role + ", favoritePickles=" + favoritePickles + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}