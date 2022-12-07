package com.keencho.spring.jpa.service;

import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

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

            childService.throwException();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Transactional
    // 4. tx rollback (loop)
    public void jpaTxRollbackLoop() {
        for (var i = 0; i < 10; i ++) {
            try {
                var delivery = deliveryRepository.findById(1L).orElseThrow(() -> new RuntimeException("delivery entity not exist!"));

                delivery.setDtDeliveryStartedAt(LocalDateTime.now());

                deliveryRepository.save(delivery);

                if (i == 9) {
                    childService.throwException();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Transactional
    // 5. tx rollback (async loop)
    public void jpaTxRollbackAsyncLoop() throws ExecutionException, InterruptedException {
        var fjp = new ForkJoinPool(4);
        fjp.submit(() -> {
            IntStream.range(0, 10).parallel().forEach((idx) -> {
                try {
                    var delivery = deliveryRepository.findById(1L).orElseThrow(() -> new RuntimeException("delivery entity not exist!"));

                    delivery.setDtDeliveryStartedAt(LocalDateTime.now());

                    deliveryRepository.save(delivery);

                    childService.throwException();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }).get();
    }
}
