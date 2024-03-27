package com.pickle.pickledemo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String roleName;

    @Column(name = "claims")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Claims> roleClaims;

    public Roles(String roleName, List<Claims> roleClaims) {
        this.roleName = roleName;
        this.roleClaims = roleClaims;
    }

    public Roles(String roleName) {
        this.roleName = roleName;
    }

    public Roles(int id, List<Claims> roleClaims) {
        this.id = id;
        this.roleClaims = roleClaims;
    }

    @Override
    public String toString() {
        return "Roles{" + "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleClaims=" + roleClaims +
                '}';
    }
}
