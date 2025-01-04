package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    @NotNull(message = "is required")
    private String title;

    @NotNull(message = "is required")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "must be a valid email address")
    private String email;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    @NotNull(message = "is required")
    private Integer ownerUserId;

    @NotNull(message = "is required")
    private String ownerUserEmail;

    private String accountImageUrl;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<User> users = new HashSet<>();

}
