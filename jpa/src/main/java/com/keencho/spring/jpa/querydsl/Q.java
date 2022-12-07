package com.keencho.spring.jpa.querydsl;

import com.keencho.spring.jpa.querydsl.model.QDelivery;
import com.keencho.spring.jpa.querydsl.model.QDeliveryHistory;
import com.keencho.spring.jpa.querydsl.model.QOrder;
import com.querydsl.core.types.Path;

import java.util.HashMap;
import java.util.Map;

public class Q {
    public static QOrder order = QOrder.order;
    public static QDelivery delivery = QDelivery.delivery;
    public static QDeliveryHistory deliveryHistory = QDeliveryHistory.deliveryHistory;

    public static Map<Path<?>, Object> newUpdateMap() {
        return new HashMap<>();
    }
}
