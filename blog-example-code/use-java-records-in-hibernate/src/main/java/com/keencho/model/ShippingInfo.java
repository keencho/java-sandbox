package com.keencho.model;

import jakarta.persistence.Embeddable;

@Embeddable
//@EmbeddableInstantiator(ShippingInfoInstantiator.class)
public record ShippingInfo(
        String name,
        String address,
        String number
) { }
