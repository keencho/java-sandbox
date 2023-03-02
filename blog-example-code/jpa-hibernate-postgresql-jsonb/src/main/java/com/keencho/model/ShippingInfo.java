package com.keencho.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ShippingInfo implements Serializable {
    private String name;
    private String number;
    private String address;
}
