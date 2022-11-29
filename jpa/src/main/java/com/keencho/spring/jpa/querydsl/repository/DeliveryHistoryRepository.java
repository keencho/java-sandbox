package com.keencho.spring.jpa.querydsl.repository;

import com.keencho.lib.spring.jpa.querydsl.repository.KcJpaRepository;
import com.keencho.spring.jpa.querydsl.model.DeliveryHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryHistoryRepository extends KcJpaRepository<DeliveryHistory, Long> {
}
