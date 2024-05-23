package com.pickle.pickledemo.pojos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
