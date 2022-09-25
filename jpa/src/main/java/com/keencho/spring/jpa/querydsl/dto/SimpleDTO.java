package com.keencho.spring.jpa.querydsl.dto;

import com.keencho.lib.spring.jpa.querydsl.annotation.KcQueryProjection;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@KcQueryProjection
public class SimpleDTO {
    Long deliveryId;
    Long orderId;
    String field;
}
