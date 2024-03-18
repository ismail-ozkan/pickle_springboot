package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id") // it's optional
    private int id;
    @Column(name = "city")
    private String city;
    @Column(name = "full_address")
    private String fullAddress;

    //Use mappedBy attribute if the relationship is bidirectional.
//    @OneToOne(mappedBy = "address")
//    private Users user;

    // I decided to use Lombok annotations here


}
