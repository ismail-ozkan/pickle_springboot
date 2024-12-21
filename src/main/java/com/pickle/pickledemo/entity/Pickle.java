package com.pickle.pickledemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pickle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "is required")
    private String name;

    @NotNull(message = "is required")
    private Integer price;

    private Integer cost;

    //TODO: enum olarak pickleType eklenecek, çeşidine göre fiyatı otomatik belirlenecek. Örneğin typelar 1, 2, 3, 5 kg. Turşu eklenirken type bilgisi verilecek ve 1 kg lıkda cost * 1.5 olacak price, 5 likte 1.2
    //private ENUM pickleType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    private Boolean isActive;

    private Integer sellerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private File file;

//    @ManyToMany(mappedBy = "favoritePickles")
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private List<User> usersWhoFavorited;

}
