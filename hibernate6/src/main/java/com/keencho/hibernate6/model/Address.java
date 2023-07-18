package com.keencho.hibernate6.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String address1;
    private String address2;
    private String name;
    private String contact;
}
