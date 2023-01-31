package com.keencho.application.dto;

import com.keencho.application.model.Gender;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDTO {
    UUID id;
    String name;
    LocalDateTime dtCreatedAt;

    UUID productId;
    String productName;
    long productPrice;

    UUID customerId;
    String customerName;
    int customerAge;
    Gender customerGender;
}
