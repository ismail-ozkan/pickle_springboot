package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    //@Column(name = "id") // it's optional
    private int id;

    private String city;

    private String fullAddress;

    //Use mappedBy attribute if the relationship is bidirectional.
//    @OneToOne(mappedBy = "address")
//    private Users user;

    // I decided to use Lombok annotations here


}
