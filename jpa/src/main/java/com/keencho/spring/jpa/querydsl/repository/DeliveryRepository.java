package com.keencho.spring.jpa.querydsl.repository;

import com.keencho.spring.jpa.querydsl.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
