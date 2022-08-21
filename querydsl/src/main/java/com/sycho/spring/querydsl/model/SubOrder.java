package com.sycho.spring.querydsl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MainOrder mainOrder;

    private boolean active;

    private int itemCount;
    private int itemPrice;

    @Formula("item_count * item_price")
    private int totalPrice;

}