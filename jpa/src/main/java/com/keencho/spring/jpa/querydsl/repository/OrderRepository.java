package com.keencho.spring.jpa.querydsl.repository;

import com.keencho.spring.jpa.querydsl.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
