package com.keencho.spring.jpa.querydsl.dto;

import com.keencho.lib.spring.jpa.querydsl.annotation.KcQueryProjection;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
@KcQueryProjection
public class SimpleDTO {
    Long deliveryId;
    Long orderId;

    @QueryProjection
    public SimpleDTO(Long deliveryId, Long orderId) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
    }
}
