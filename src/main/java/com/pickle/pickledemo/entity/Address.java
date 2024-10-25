package com.pickle.pickledemo.entity;

import lombok.*;

import javax.persistence.*;
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
