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
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long deliveryId;

    String fromName;
    String fromNumber;
    String fromAddress;

    String toName;
    String toNumber;
    String toAddress;

    LocalDateTime dtDeliveryStartedAt;
    LocalDateTime dtDeliveryFinishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    Order order;
}
