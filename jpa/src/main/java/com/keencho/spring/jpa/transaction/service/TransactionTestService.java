package com.keencho.spring.jpa.transaction.service;

import com.keencho.spring.jpa.transaction.model.TransactionTestModel;
import com.keencho.spring.jpa.transaction.repository.TransactionTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class TransactionTestService {

    @Autowired
    TransactionTestRepository transactionTestRepository;

    public TransactionTestModel save(String payKey, int amount) {

        transactionTestRepository
                .findByPayKey(payKey)
                .ifPresent(i -> {
                    throw new RuntimeException("이미 데이터가 존재합니다.");
                });

        TransactionTestModel transactionTestModel = new TransactionTestModel();

        transactionTestModel.setPayKey(payKey);
        transactionTestModel.setAmount(amount);

        return transactionTestRepository.save(transactionTestModel);
    }
}
