package com.pickle.pickledemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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


    @ManyToMany(mappedBy = "favoritePickles")
//            (fetch = FetchType.LAZY,
//    cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType. DETACH, CascadeType.REFRESH})
//    @JoinTable(
//            name = "user_favorite_pickles",
//            joinColumns = @JoinColumn(name = "pickle_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonBackReference
    private List<User> usersWhoFavorited;


    @Override
    public String toString() {
        return "Pickle{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", cost=" + cost + ", createdDate=" + createdDate + ", sellerId=" + sellerId + ", usersWhoFavorited=" + usersWhoFavorited + '}';
    }
}
