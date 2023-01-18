package com.keencho.model;

import com.blazebit.persistence.CTE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

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
