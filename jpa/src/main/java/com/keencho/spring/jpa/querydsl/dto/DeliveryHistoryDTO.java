package com.keencho.spring.jpa.querydsl.dto;

import com.keencho.lib.spring.jpa.querydsl.annotation.KcQueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@KcQueryProjection
public class DeliveryHistoryDTO {
    Long id;
    String text;
    DeliveryDTO deliveryDTO;
}
