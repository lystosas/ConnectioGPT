package com.businessgroup.pos_saas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomApiResponse<T> {
    private int status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;

    // Constructor sin necesidad de pasar timestamp
    public CustomApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}
