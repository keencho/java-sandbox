package com.keencho.application.repository;

import com.keencho.application.model.Order;
import com.keencho.application.model.Product;
import com.keencho.application.repository.custom.CustomJpaQueryDSLRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CustomJpaQueryDSLRepository<Order, UUID> {

    List<Order> findByProductNameContaining(String productName);

    Page<Order> findByNameContaining(String name, Pageable pageable);

    List<Order> findByProductPriceGreaterThanOrderByNameDesc(Long price);

    Page<Order> findByProductPriceGreaterThan(Long price, Pageable pageable);

}
