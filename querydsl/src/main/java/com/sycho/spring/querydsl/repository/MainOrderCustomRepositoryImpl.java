package com.sycho.spring.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainOrderCustomRepositoryImpl implements MainOrderCustomRepository{

    @Autowired
    JPAQueryFactory jpaQueryFactory;

}
