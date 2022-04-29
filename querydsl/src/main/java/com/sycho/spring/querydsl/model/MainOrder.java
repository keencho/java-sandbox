package com.sycho.spring.querydsl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "main_order")
public class MainOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fromName;
    private String fromPhoneNumber;
    private String fromAddress;
    @Embedded
    private Coordinates fromCoordinates;
    @ManyToOne(fetch = FetchType.LAZY)
    private Rider fromRider;

    private String toName;
    private String toPhoneNumber;
    private String toAddress;
    @Embedded
    private Coordinates toCoordinates;
    @ManyToOne(fetch = FetchType.LAZY)
    private Rider toRider;
}
