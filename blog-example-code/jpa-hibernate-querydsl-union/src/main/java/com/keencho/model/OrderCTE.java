package com.keencho.model;

import com.blazebit.persistence.CTE;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@CTE
@Entity
public class OrderCTE {

    @Id
    String id;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    String fromAddress;
    String fromName;
    String fromPhoneNumber;

    String toAddress;
    String toName;
    String toPhoneNumber;

    String itemName;
    int itemPrice;

    LocalDateTime createdDateTime;
}
