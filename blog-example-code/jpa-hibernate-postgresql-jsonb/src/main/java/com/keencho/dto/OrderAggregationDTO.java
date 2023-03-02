package com.keencho.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class OrderAggregationDTO {
    private String lastName;
    private long count;

    @QueryProjection
    public OrderAggregationDTO(String lastName, long count) {
        this.lastName = lastName;
        this.count = count;
    }
}
