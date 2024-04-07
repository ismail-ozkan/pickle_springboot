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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String roleName;

    @Column(name = "claims")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Claim> roleClaims;

    public Role(String roleName, List<Claim> roleClaims) {
        this.roleName = roleName;
        this.roleClaims = roleClaims;
    }

    public Role(String roleName) {
        setRoleName(roleName);
    }

    public void setRoleName(String roleName) {
        this.roleName = "ROLE_"+roleName;
    }

    public Role(int id, List<Claim> roleClaims) {
        this.id = id;
        this.roleClaims = roleClaims;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleClaims=" + roleClaims +
                '}';
    }
}
