package com.keencho.spring.jpa.querydsl.dto;

import com.keencho.lib.spring.jpa.querydsl.annotation.KcQueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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
}
