package com.keencho.spring.jpa.querydsl.dto;

import com.keencho.lib.spring.jpa.querydsl.KcProjectionExpression;

public class SimpleKcProjectionExpressionDTO extends KcProjectionExpression<SimpleDTO> {
    public SimpleKcProjectionExpressionDTO() {
        super(SimpleDTO.class);
    }

    private com.querydsl.core.types.Expression<Long> deliveryId;

    public void setDeliveryId(com.querydsl.core.types.Expression<Long> deliveryId) {
        this.deliveryId = deliveryId;
    }

    private com.querydsl.core.types.Expression<Long> orderId;

    public void setOrderId(com.querydsl.core.types.Expression<Long> orderId) {
        this.orderId = orderId;
    }

    private com.querydsl.core.types.Expression<String> field;

    public void setField(com.querydsl.core.types.Expression<String> field) {
        this.field = field;
    }
}
