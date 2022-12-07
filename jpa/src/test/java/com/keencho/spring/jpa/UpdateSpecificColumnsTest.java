package com.keencho.spring.jpa;

import com.keencho.spring.jpa.querydsl.Q;
import com.keencho.spring.jpa.querydsl.model.QDelivery;
import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import com.keencho.spring.jpa.service.QueryDSLUpdateTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class UpdateSpecificColumnsTest {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    QueryDSLUpdateTransactionService service;

    @Test
    public void jpaDirtyChecking() {
        service.jpaDirtyChecking();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    // 자식 서비스에서 unchecked exception 예외 발생 -> marked as rollback-only
    public void jpaTxRollback() {
        service.jpaTxRollback();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    // querydsl의 update절을 사용했지만 위와 동일하게 rollback됨
    // 결국 동일한 transactional 로직을 따른다는것을 확인할 수 있음.
    public void queryDSLTxRollback() {
        service.queryDSLTxRollback();
    }

    @Test
    public void queryDSLTest() {
        var q = QDelivery.delivery;
        var map = Q.newUpdateMap();

        map.put(q.dtDeliveryStartedAt, LocalDateTime.now());

        deliveryRepository.updateOne(q.deliveryId.eq(1L), map);
    }
}
