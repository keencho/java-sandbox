package com.sycho.spring.querydsl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sub_order")
public class SubOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fromName;
    private String fromPhoneNumber;
    private String fromAddress;

    private String toName;
    private String toPhoneNumber;
    private String toAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Rider fromRider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Rider toRider;

    @ManyToOne(optional = false)
    private MainOrder mainOrder;

    private boolean active;

}
