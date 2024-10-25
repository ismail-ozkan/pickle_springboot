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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    private Integer sellerId;

    @ManyToMany(mappedBy = "favoritePickles")
    @JsonBackReference
    private List<User> usersWhoFavorited;


    @Override
    public String toString() {
        return "Pickle{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", cost=" + cost + ", createdDate=" + createdDate + ", sellerId=" + sellerId + ", usersWhoFavorited=" + usersWhoFavorited + '}';
    }
}
