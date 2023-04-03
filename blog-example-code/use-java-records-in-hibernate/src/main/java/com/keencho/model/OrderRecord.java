package com.keencho.model;

import java.time.LocalDateTime;

public record OrderRecord(
        String id,
        OrderStatus status,
        ShippingInfo fromShippingInfo,
        ShippingInfo toShippingInfo,
        String itemName,
        int itemPrice,
        LocalDateTime createdDateTime
) { }
