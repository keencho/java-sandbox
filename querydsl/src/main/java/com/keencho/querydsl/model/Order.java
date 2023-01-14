package com.keencho.querydsl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Enumerated(EnumType.STRING)
    protected OrderStatus status;

    protected String fromAddress;
    protected String fromName;
    protected String fromPhoneNumber;

    protected String toAddress;
    protected String toName;
    protected String toPhoneNumber;

    protected String itemName;
    protected int itemPrice;

    protected LocalDateTime createdDateTime;
}
