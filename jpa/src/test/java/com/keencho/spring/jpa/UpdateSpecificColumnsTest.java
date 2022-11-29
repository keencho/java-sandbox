package com.keencho.spring.jpa;

import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.model.Delivery;
import com.keencho.spring.jpa.querydsl.model.QDelivery;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import com.keencho.spring.jpa.utils.FakerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
//@Transactional
//@Rollback(value = false)
public class UpdateSpecificColumnsTest {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Test
    public void jpaDirtyChecking() {
        var delivery = deliveryRepository.findById(1L).orElseThrow(() -> new RuntimeException("delivery entity not exist"));

        delivery.setDtDeliveryStartedAt(LocalDateTime.now());
    }

    @Test
    public void queryDSLTest() {
        var q = QDelivery.delivery;
        var map = Q.newUpdateMap();

        map.put(q.dtDeliveryStartedAt, LocalDateTime.now());

        deliveryRepository.updateOne(q.deliveryId.eq(1L), map);
    }
}
