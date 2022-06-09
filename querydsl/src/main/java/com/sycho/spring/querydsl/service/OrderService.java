package com.sycho.spring.querydsl.service;

import com.sycho.spring.querydsl.model.MainOrder;
import com.sycho.spring.querydsl.model.SubOrder;
import com.sycho.spring.querydsl.repository.MainOrderRepository;
import com.sycho.spring.querydsl.repository.SubOrderRepository;
import com.sycho.spring.querydsl.util.JavaFakerGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    MainOrderRepository mainOrderRepository;

    @Autowired
    SubOrderRepository subOrderRepository;

    @PostConstruct
    public void initData() {

        List<MainOrder> mainOrderList = new ArrayList<>();

        for (int i = 0; i < 5; i ++) {
            var mainOrder = new MainOrder();
            mainOrderList.add(mainOrderRepository.save(mainOrder));
        }

        for (int i = 0; i < 100; i ++) {
            var subOrder = new SubOrder();
            subOrder.setActive(i % 3 == 0);

            subOrder.setFromName(JavaFakerGenerator.getName());
            subOrder.setFromPhoneNumber(JavaFakerGenerator.getPhoneNumber());
            subOrder.setFromAddress(JavaFakerGenerator.getAddress());

            subOrder.setToName(JavaFakerGenerator.getName());
            subOrder.setToPhoneNumber(JavaFakerGenerator.getPhoneNumber());
            subOrder.setToAddress(JavaFakerGenerator.getAddress());

            subOrder.setMainOrder(mainOrderList.get(i % 5));
            subOrderRepository.save(subOrder);
        }
    }
}
