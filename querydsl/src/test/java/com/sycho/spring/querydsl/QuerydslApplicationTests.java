package com.sycho.spring.querydsl;

import com.sycho.spring.querydsl.repository.MainOrderRepository;
import com.sycho.spring.querydsl.repository.SubOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class QuerydslApplicationTests {

    @Autowired
    MainOrderRepository mainOrderRepository;

    @Autowired
    SubOrderRepository subOrderRepository;

    @Test
    @Transactional
    void test() {
//        var m1 = mainOrderRepository.findAll();
        var m2 = mainOrderRepository.test();

//        m2.stream().map(order -> order.getFromNameLikeCList().size()).toList().forEach(System.out::println);
    }

}
