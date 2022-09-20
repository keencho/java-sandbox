package com.keencho.spring.jpa;

import com.keencho.lib.spring.jpa.querydsl.KcGenericExporter;
import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.dto.DeliveryDTO;
import com.keencho.spring.jpa.querydsl.dto.QDeliveryDTO;
import com.keencho.spring.jpa.querydsl.dto.QSimpleDTO;
import com.keencho.spring.jpa.querydsl.dto.QSimpleDTOV2;
import com.keencho.spring.jpa.querydsl.model.Delivery;
import com.keencho.spring.jpa.querydsl.model.Order;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import com.keencho.spring.jpa.querydsl.repository.OrderRepository;
import com.keencho.spring.jpa.utils.FakerUtils;
import com.querydsl.codegen.Keywords;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

@SpringBootTest
@Transactional
public class QueryDSLTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    OrderRepository orderRepository;

    Random rand = new Random();

    @BeforeEach
    public void initData() {
        var orderList = new ArrayList<Order>();

        for (long i = 1; i <= 5; i ++) {
            var order = new Order();
            order.setOrderId(i);
            var o = orderRepository.save(order);

            orderList.add(o);
        }

        for (long i = 1; i <= 100; i ++) {
            var delivery = new Delivery();
            delivery.setDeliveryId(i);

            delivery.setFromName(FakerUtils.name());
            delivery.setFromNumber(FakerUtils.phoneNumber());
            delivery.setFromAddress(FakerUtils.address());

            delivery.setToName(FakerUtils.name());
            delivery.setToNumber(FakerUtils.phoneNumber());
            delivery.setToAddress(FakerUtils.address());

            delivery.setOrder(orderList.get(rand.nextInt(orderList.size())));

            deliveryRepository.save(delivery);
        }
    }

    @Test
    public void caseWhenTest() {
        var q = Q.delivery;
        var bb = new BooleanBuilder();

        bb.and(q.fromName.startsWithIgnoreCase("a"));

        var i = queryFactory
                .select(new QDeliveryDTO(
                        q.deliveryId,
                        q.fromName,
                        q.fromNumber,
                        q.fromAddress,
                        new CaseBuilder().when(q.fromNumber.startsWith("1")).then(true).otherwise(false),
                        q.toName,
                        q.toNumber,
                        q.toAddress,
                        q.dtDeliveryStartedAt,
                        q.dtDeliveryFinishedAt,
                        q.order.orderId
                ))
                .from(q)
                .where(bb)
                .fetch();


        var condition = i.stream().filter(v -> v.getFromNumber().startsWith("1")).allMatch(DeliveryDTO::isFromNumberStartWith1);

        Assert.isTrue(condition, "case when not work properly!");
    }

    @Test
    void dtoTest() {
        var q = Q.delivery;
        var dto = QSimpleDTOV2.builder()
                .deliveryId(q.deliveryId)
                .orderId(q.order.orderId)
                .build();

        var dto2 = QSimpleDTOV2.constructor();
        dto2.setDeliveryId(q.deliveryId);
        dto2.setOrderId(q.order.orderId);

        var i = queryFactory
                .select(dto)
                .from(q)
                .fetch();

        var i2 = queryFactory
                .select(dto2.build())
                .from(q)
                .fetch();

        Assert.isTrue(i.size() == i2.size(), "check!");
    }
}
