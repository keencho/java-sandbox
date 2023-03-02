package com.keencho.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@Table(name = "order_new")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    ShippingInfo fromInfo;

    @JdbcTypeCode(SqlTypes.JSON)
    ShippingInfo toInfo;
}
