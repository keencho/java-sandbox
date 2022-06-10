package com.sycho.spring.querydsl;

import com.mysema.commons.lang.Assert;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sycho.spring.querydsl.model.MainOrder;
import com.sycho.spring.querydsl.model.QMainOrder;
import com.sycho.spring.querydsl.model.QSubOrder;
import com.sycho.spring.querydsl.repository.MainOrderRepository;
import com.sycho.spring.querydsl.repository.SubOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class QuerydslApplicationTests {

    @Autowired
    MainOrderRepository mainOrderRepository;

    @Autowired
    SubOrderRepository subOrderRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    @Transactional
    void whereAnnotationTest1() {
        var mainOrderList = mainOrderRepository.findAll();

        mainOrderList.forEach(mainOrder -> {
            mainOrder.getActiveSubOrderList();
            mainOrder.getDeActivatedSubOrderList();
            mainOrder.getFromNameLikeCList();
        });
    }

    @Test
    @Transactional
    void whereAnnotationTest2() {

        var mq = QMainOrder.mainOrder;
        var sq = QSubOrder.subOrder;

        var subQueryFetch = jpaQueryFactory
                .select(mq)
                .from(mq)
                .where(JPAExpressions.select(sq.mainOrder.count()).from(sq).where(mq.eq(sq.mainOrder).and(sq.fromName.startsWithIgnoreCase("c"))).gt(0L))
                .fetch();

        var whereAnnotationFetch = jpaQueryFactory
                .select(mq)
                .from(mq)
                .where(mq.fromNameLikeCList.size().gt(0L))
                .fetch();

        Assert.isTrue(subQueryFetch.size() == whereAnnotationFetch.size(), "test failed");
    }

}
