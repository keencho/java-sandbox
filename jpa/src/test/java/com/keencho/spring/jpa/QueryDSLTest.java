package com.keencho.spring.jpa;

import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.dto.KcQDeliveryDTO;
import com.keencho.spring.jpa.querydsl.dto.KcQDeliveryHistoryDTO;
import com.keencho.spring.jpa.querydsl.dto.KcQSimpleDTO;
import com.keencho.spring.jpa.querydsl.model.Delivery;
import com.keencho.spring.jpa.querydsl.model.DeliveryHistory;
import com.keencho.spring.jpa.querydsl.model.Order;
import com.keencho.spring.jpa.querydsl.model.QDelivery;
import com.keencho.spring.jpa.querydsl.repository.DeliveryHistoryRepository;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import com.keencho.spring.jpa.querydsl.repository.OrderRepository;
import com.keencho.spring.jpa.utils.FakerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.querydsl.QSort;
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

    @Autowired
    DeliveryHistoryRepository deliveryHistoryRepository;

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

            var d = deliveryRepository.save(delivery);

            var deliveryHistory1 = new DeliveryHistory();
            deliveryHistory1.setDeliveryId(d.getDeliveryId());
            deliveryHistory1.setText("[생성] 배송이 생성되었습니다.");

            deliveryHistoryRepository.save(deliveryHistory1);

            var deliveryHistory2 = new DeliveryHistory();
            deliveryHistory2.setDeliveryId(d.getDeliveryId());
            deliveryHistory2.setText("[배차] 배송이 배차되었습니다.");

            deliveryHistoryRepository.save(deliveryHistory2);
        }
    }

    @Test
    public void queryTest() {
        var q = Q.delivery;

        var deliveryDTO = new KcQDeliveryDTO();
        deliveryDTO.setFromAddress(q.fromAddress);
        deliveryDTO.setFromName(q.fromName);
        deliveryDTO.setFromNumber(q.fromNumber);

        var simpleDTO = KcQSimpleDTO.builder()
                .orderId(q.order.orderId)
                .deliveryId(q.deliveryId)
                .deliveryDTO(deliveryDTO.build())
                .build();

        var sort = new QSort(q.order.orderId.asc(), q.deliveryId.desc());

        var list = deliveryRepository.selectList(null, simpleDTO, null, sort);

        System.out.println(list.size());
    }

    @Test
    public void queryHandlerTest() {
        var q = Q.deliveryHistory;
        var dq = Q.delivery;

        var dDTO = KcQDeliveryDTO.builder()
                .fromAddress(dq.fromAddress)
                .fromName(dq.fromName)
                .build();

        var dto = KcQDeliveryHistoryDTO.builder()
                .id(q.id)
                .text(q.text)
                .deliveryDTO(dDTO.build())
                .build();

        var list = deliveryHistoryRepository
                .selectList(
                        null,
                        dto,
                        (query) -> query.leftJoin(dq).on(dq.deliveryId.eq(q.deliveryId)),
                        null);

        System.out.println(list.size());

    }
}
