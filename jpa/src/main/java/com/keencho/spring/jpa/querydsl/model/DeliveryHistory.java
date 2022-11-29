package com.keencho.spring.jpa.querydsl.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "delivery_history")
public class DeliveryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    // join test를 위해 일부로 연관관계 매핑 x
    Long deliveryId;

    String text;

    LocalDateTime dtCreatedAt = LocalDateTime.now();
}
