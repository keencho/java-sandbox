package com.keencho.application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "od")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    Product product;

    UUID customerId;

    LocalDateTime dtCreatedAt = LocalDateTime.now();
}
