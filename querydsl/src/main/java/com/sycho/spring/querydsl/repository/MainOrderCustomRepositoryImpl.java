package com.sycho.spring.querydsl.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sycho.spring.querydsl.model.MainOrder;
import com.sycho.spring.querydsl.model.QMainOrder;
import com.sycho.spring.querydsl.model.QSubOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainOrderCustomRepositoryImpl implements MainOrderCustomRepository{

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MainOrder> test() {
        var mq = QMainOrder.mainOrder;
        var sq = QSubOrder.subOrder;

        var list = jpaQueryFactory
                .select(mq.id, mq.orderDate)
                .from(mq)
                .orderBy(
                        new OrderSpecifier<>(
                                Order.DESC,
                                JPAExpressions.select(sq.mainOrder.id.count()).from(sq).where(sq.mainOrder.id.eq(mq.id))
                        )
                )
                .fetch();

        return jpaQueryFactory
                .select(mq)
                .from(mq)
                .orderBy(mq.fromNameLikeCList.size().desc())
                .fetch();
    }
}
