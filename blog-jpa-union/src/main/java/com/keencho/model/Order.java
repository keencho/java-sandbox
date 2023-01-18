package com.keencho.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Order {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, length = 32)
    String id;

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
