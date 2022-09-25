package com.keencho.spring.jpa.querydsl.repository;

import com.keencho.lib.spring.jpa.querydsl.repository.KcJpaRepository;
import com.keencho.spring.jpa.querydsl.model.Delivery;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends KcJpaRepository<Delivery, Long> {
}
