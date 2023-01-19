package com.keencho.model;

import com.querydsl.core.annotations.QueryProjection;

public class Result {
    String toName;
    String toAddress;
    int itemPrice;
    String itemName;

    @QueryProjection
    public Result(String toName, String toAddress, int itemPrice, String itemName) {
        this.toName = toName;
        this.toAddress = toAddress;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
    }
}
