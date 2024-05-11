package com.pickle.pickledemo.entity;

import lombok.*;

import javax.persistence.*;
@Data
@Builder
@Entity
@Table(name = "claims")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id") // it's optional
    private int id;

    @Column(name = "name")
    private String claimName;

    @Column(name = "type")
    private String claimType;

    public Claim(String claimName, String claimType) {
        this.claimName = claimName;
        this.claimType = claimType;
    }

    @Override
    public String toString() {
        return "Claim{" + "id=" + id +
                ", claimName='" + claimName + '\'' +
                ", claimType='" + claimType + '\'' +
                '}';
    }
}
