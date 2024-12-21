package com.pickle.pickledemo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String orderNumber;

    @Column
    private Integer totalAmount;

    @Column
    @Enumerated(EnumType.STRING) // ENUM'u string olarak saklar.
    private OrderStatus status; // Enum: NEW, PROCESSING, COMPLETED, CANCELED

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;// customer information

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems; // Sipariş içerikleri

    @Column(length = 500)
    private String notes; // Ek açıklamalar

//    @PrePersist // Kaydedilmeden önce çalışır
//    protected void onCreate() {
//        createdDate =
//        updatedDate = LocalDateTime.now();
//    }

    /*@PreUpdate // Güncellenmeden önce çalışır
    protected void onUpdate() {
        updatedDate = LocalDate.now().
    }*/
}