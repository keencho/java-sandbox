package com.keencho.spring.jpa.transaction.repository;

import com.keencho.spring.jpa.transaction.model.TransactionTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTestRepository  extends JpaRepository<TransactionTestModel, Long> {
    Optional<TransactionTestModel> findByPayKey(String payKey);
}
