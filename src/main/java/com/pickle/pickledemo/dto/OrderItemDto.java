package com.pickle.pickledemo.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {

    private Integer id; // Order Item ID
    private Integer pickleId; // Product name
    private Integer price; // Price per unit
    private Integer quantity; // Quantity ordered
}