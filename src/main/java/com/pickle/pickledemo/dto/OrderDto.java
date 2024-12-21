package com.pickle.pickledemo.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Provides a builder pattern for object creation
public class OrderDto {

    private Integer id; // Order ID
    private String orderNumber; // Unique order number
    private BigDecimal totalAmount; // Total amount for the order
    private String status; // Order status (e.g., NEW, PROCESSING)
    private LocalDateTime createdDate; // Order creation date
    private LocalDateTime updatedDate; // Order update date
    private Integer userId; // ID of the user who placed the order
    private Integer accountId; // Associated account ID
    private String notes; // Additional notes
    private List<OrderItemDto> orderItems; // List of order items
}