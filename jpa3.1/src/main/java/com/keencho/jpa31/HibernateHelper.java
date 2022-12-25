package com.keencho.jpa31;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class HibernateHelper {

    private static final HibernateHelper instance = new HibernateHelper();

    @Getter
    private final EntityManagerFactory entityManagerFactory;

    @Getter
    private final EntityManager entityManager;

    private HibernateHelper() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public static void init() {
        instance.getEntityManager().getTransaction().begin();
    }

    public static void persist(Object entity) {
        instance.getEntityManager().persist(entity);
        instance.getEntityManager().getTransaction().commit();
    }
}
