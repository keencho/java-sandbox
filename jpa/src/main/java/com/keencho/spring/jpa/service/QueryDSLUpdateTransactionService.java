package com.keencho.spring.jpa.service;

import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class QueryDSLUpdateTransactionService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    QueryDSLUpdateTransactionChildService childService;

    @Transactional
    // 1. JPA dirty checking
    public void jpaDirtyChecking() {
        var delivery = deliveryRepository.findById(1L).orElseThrow(() -> new RuntimeException("delivery entity not exist!"));

        delivery.setDtDeliveryStartedAt(LocalDateTime.now());
    }

    @Transactional
    // 2. tx rollback
    public void jpaTxRollback() {
        try {
            var delivery = deliveryRepository.findById(1L).orElseThrow(() -> new RuntimeException("delivery entity not exist!"));

            delivery.setDtDeliveryStartedAt(LocalDateTime.now());

            deliveryRepository.save(delivery);

            childService.throwException();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Transactional
    // 3. querydsl tx rollback (?)
    public void queryDSLTxRollback() {
        try {
            var q = Q.delivery;
            var map = Q.newUpdateMap();

            map.put(q.dtDeliveryStartedAt, LocalDateTime.now());

            deliveryRepository.updateOne(q.deliveryId.eq(1L), map);

//            childService.throwException();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
