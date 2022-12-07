package com.keencho.spring.jpa.service;

import com.keencho.spring.jpa.querydsl.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QueryDSLUpdateTransactionChildService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Transactional
    public void throwException() {
        throw new RuntimeException("Exception!");
    }
}
