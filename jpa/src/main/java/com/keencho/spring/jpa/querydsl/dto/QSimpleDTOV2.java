package com.keencho.spring.jpa.querydsl.dto;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;

public class QSimpleDTOV2 extends ConstructorExpression<SimpleDTO> {

    public QSimpleDTOV2(Expression<Long> p0, Expression<Long> p1) {
        super(SimpleDTO.class, new Class<?>[]{long.class, long.class}, p0, p1);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder constructor() {
        return builder();
    }

    public static class Builder {

        private Expression<Long> deliveryId;
        private Expression<Long> orderId;

        public Builder() {
        }

        public Builder deliveryId(Expression<Long> deliveryId) {
            this.deliveryId = deliveryId;
            return this;
        }

        public Builder orderId(Expression<Long> orderId) {
            this.orderId = orderId;
            return this;
        }

        public void setDeliveryId(Expression<Long> deliveryId) {
            this.deliveryId = deliveryId;
        }

        public void setOrderId(Expression<Long> orderId) {
            this.orderId = orderId;
        }

        public QSimpleDTOV2 build() {
            return new QSimpleDTOV2(deliveryId, orderId);
        }
    }
}
