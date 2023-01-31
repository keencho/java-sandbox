package com.keencho.application.utils;

import com.querydsl.jpa.impl.JPAQuery;

@FunctionalInterface
public interface QueryHandler {
    JPAQuery<?> apply(JPAQuery<?> query);
}
