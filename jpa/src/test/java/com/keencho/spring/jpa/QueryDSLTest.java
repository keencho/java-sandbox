package com.keencho.spring.jpa;

import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.dto.KcQSimpleDTO;
import com.keencho.spring.jpa.querydsl.dto.QSimpleDTO;
import com.keencho.spring.jpa.querydsl.dto.SimpleKcProjectionExpressionDTO;
import com.keencho.spring.jpa.querydsl.model.Delivery;
import com.keencho.spring.jpa.querydsl.model.Order;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import com.keencho.spring.jpa.querydsl.repository.OrderRepository;
import com.keencho.spring.jpa.utils.FakerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        var dto = new KcQSimpleDTO();
        dto.setOrderId(q.order.orderId);
        dto.setDeliveryId(q.deliveryId);

        var dto2 = KcQSimpleDTO.builder()
                .orderId(q.order.orderId)
                .deliveryId(q.deliveryId)
                .build();


        var list = deliveryRepository.selectList(null, dto2);

        System.out.println(list.size());
    }
}
