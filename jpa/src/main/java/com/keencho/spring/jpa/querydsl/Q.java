package com.keencho.spring.jpa.querydsl;

import com.keencho.spring.jpa.querydsl.model.QDelivery;
import com.keencho.spring.jpa.querydsl.model.QOrder;

public class Q {
    public static QOrder order = QOrder.order;
    public static QDelivery delivery = QDelivery.delivery;
}
