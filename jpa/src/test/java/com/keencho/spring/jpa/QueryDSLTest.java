package com.keencho.spring.jpa;

import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.dto.DeliveryDTO;
import com.keencho.spring.jpa.querydsl.dto.QDeliveryDTO;
import com.keencho.spring.jpa.querydsl.dto.QSimpleDTO;
import com.keencho.spring.jpa.querydsl.model.Delivery;
import com.keencho.spring.jpa.querydsl.model.Order;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import com.keencho.spring.jpa.querydsl.repository.OrderRepository;
import com.keencho.spring.jpa.utils.FakerUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Random;

@SpringBootTest
@Transactional
public class QueryDSLTest {

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
    public void reflectionTest() {
        var q = Q.delivery;

//        var list = deliveryRepository.selectList(
//                null,
//                QSimpleDTO.builder().deliveryId(q.deliveryId).orderId(q.order.orderId)
//        );
//
//        System.out.println(list.size());
    }
}
