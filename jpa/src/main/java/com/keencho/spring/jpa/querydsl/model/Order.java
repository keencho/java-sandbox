package com.keencho.spring.jpa.querydsl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "main_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long orderId;

    LocalDateTime dtCreatedAt = LocalDateTime.now();
    LocalDateTime dtUpdatedAt = LocalDateTime.now();
}
