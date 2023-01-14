package com.keencho.querydsl.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    RECEIVED("접수"),
    PACKAGING("포장"),
    SHIPPING("배송중"),
    COMPLETED("완료"),
    FAILED("실패");

    private final String description;
}
