package com.keencho.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String fromAddress;
    private String fromName;
    private String fromNumber;
    private String toAddress;
    private String toName;
    private String toNumber;

    @QueryProjection
    public OrderDTO(Long id, String fromAddress, String fromName, String fromNumber, String toAddress, String toName, String toNumber) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.fromName = fromName;
        this.fromNumber = fromNumber;
        this.toAddress = toAddress;
        this.toName = toName;
        this.toNumber = toNumber;
    }
}
