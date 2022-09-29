package com.keencho.spring.jpa.querydsl.dto;

import com.keencho.lib.spring.jpa.querydsl.annotation.KcQueryProjection;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@KcQueryProjection
public class SimpleDTO {
    Long deliveryId;
    Long orderId;
    String field;

    @QueryProjection
    public SimpleDTO(Long deliveryId, Long orderId, String field) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.field = field;
    }
}