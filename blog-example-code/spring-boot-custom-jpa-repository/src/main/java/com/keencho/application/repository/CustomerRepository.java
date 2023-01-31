package com.keencho.application.repository;

import com.keencho.application.model.Customer;
import com.keencho.application.repository.custom.CustomJpaQueryDSLRepository;

import java.util.UUID;

public interface CustomerRepository extends CustomJpaQueryDSLRepository<Customer, UUID> {
}
