package com.keencho.application.repository;

import com.keencho.application.model.Order;
import com.keencho.application.model.Product;
import com.keencho.application.repository.custom.CustomJpaQueryDSLRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CustomJpaQueryDSLRepository<Order, UUID> {
    List<Order> findByProductPriceGreaterThanOrderByNameDesc(Long price);
}
