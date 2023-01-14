package com.keencho.querydsl;

import com.keencho.querydsl.model.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.H2Templates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class JPAHelper {
    public static final JPAHelper INSTANCE = new JPAHelper();

    @Getter
    private final EntityManagerFactory entityManagerFactory;

    @Getter
    private final EntityManager entityManager;

    @Getter
    private final JPAQueryFactory queryFactory;

    private JPAHelper() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.queryFactory = new JPAQueryFactory(this.entityManager);
    }

    public static class Q {
        public static QOrder_2206 q1 = QOrder_2206.order_2206;
        public static QOrder_2209 q2 = QOrder_2209.order_2209;
        public static QOrder_2211 q3 = QOrder_2211.order_2211;
        public static QOrder_2212 q4 = QOrder_2212.order_2212;
        public static QOrder_2301 q5 = QOrder_2301.order_2301;

        public static JPASQLQuery<?> query() {
            return new JPASQLQuery<>(JPAHelper.INSTANCE.entityManager, new H2Templates());
        }
    }

    public static void beginTransaction() {
        INSTANCE.getEntityManager().getTransaction().begin();
    }

    public static void commit() {
        INSTANCE.getEntityManager().getTransaction().commit();
    }

    public static void close() {
        INSTANCE.getEntityManagerFactory().close();
    }

    public static void persist(Object entity) {
        INSTANCE.getEntityManager().persist(entity);
    }
}
