package com.keencho.spring.jpa.querydsl.dto;

import com.keencho.lib.spring.jpa.querydsl.annotation.KcQueryProjection;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@KcQueryProjection
public class DeliveryDTO {
    Long deliveryId;
    String fromName;
    String fromNumber;
    String fromAddress;
    boolean fromNumberStartWith1;

    String toName;
    String toNumber;
    String toAddress;

    LocalDateTime dtDeliveryStartedAt;
    LocalDateTime dtDeliveryFinishedAt;

    Long orderId;

    @QueryProjection
    @Builder
    public DeliveryDTO(Long deliveryId, String fromName, String fromNumber, String fromAddress, boolean fromNumberStartWith1, String toName, String toNumber, String toAddress, LocalDateTime dtDeliveryStartedAt, LocalDateTime dtDeliveryFinishedAt, Long orderId) {
        this.deliveryId = deliveryId;
        this.fromName = fromName;
        this.fromNumber = fromNumber;
        this.fromAddress = fromAddress;
        this.fromNumberStartWith1 = fromNumberStartWith1;
        this.toName = toName;
        this.toNumber = toNumber;
        this.toAddress = toAddress;
        this.dtDeliveryStartedAt = dtDeliveryStartedAt;
        this.dtDeliveryFinishedAt = dtDeliveryFinishedAt;
        this.orderId = orderId;
    }
}
