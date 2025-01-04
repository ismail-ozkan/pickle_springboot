package com.pickle.pickledemo.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {
    private int statusCode;
    private T data;
    private String message;
    private LocalDateTime timestamp;

    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(status.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(status.value())
                .message(message)
                .build();
        return ResponseEntity.status(status).body(response);
    }
} 