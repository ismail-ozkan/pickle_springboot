package com.pickle.pickledemo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "pickles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pickle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "is required")
    private String name;

    @Column(name = "price")
    @NotNull(message = "is required")
    private Integer price;

    @Column(name = "cost")
    private Integer cost;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Column(name = "seller_id")
    private Integer sellerId;


}
