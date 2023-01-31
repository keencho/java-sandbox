package com.keencho.application.repository;

import com.keencho.application.model.Product;
import com.keencho.application.repository.custom.CustomJpaQueryDSLRepository;

import java.util.UUID;

public interface ProductRepository extends CustomJpaQueryDSLRepository<Product, UUID> {
}
