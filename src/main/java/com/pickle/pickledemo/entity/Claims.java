package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "claims")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Claims {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id") // it's optional
    private int id;

    @Column(name = "name")
    private String claimName;

    @Column(name = "type")
    private String claimType;

    public Claims(String claimName, String claimType) {
        this.claimName = claimName;
        this.claimType = claimType;
    }

    @Override
    public String toString() {
        return "Claims{" + "id=" + id +
                ", claimName='" + claimName + '\'' +
                ", claimType='" + claimType + '\'' +
                '}';
    }
}
