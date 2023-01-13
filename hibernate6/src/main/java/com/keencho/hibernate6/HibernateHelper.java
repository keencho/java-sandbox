package com.keencho.hibernate6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper {

    public static final HibernateHelper instance = new HibernateHelper();

    @Getter
    private final EntityManagerFactory entityManagerFactory;

    @Getter
    private final EntityManager entityManager;

    private HibernateHelper() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public static void beginTransaction() {
        instance.getEntityManager().getTransaction().begin();
    }

    public static void commit() {
        instance.getEntityManager().getTransaction().commit();
    }

    public static void close() {
        instance.getEntityManagerFactory().close();
    }

    public static void persist(Object entity) {
        instance.getEntityManager().persist(entity);
    }

    ////////////////////////////////////////////////////////////////////
    /////////////////////////////////// SELECT
    ////////////////////////////////////////////////////////////////////

    @FunctionalInterface
    public interface SelectHelper {
        Selection<?> apply(CriteriaBuilder cb, Root<?> root);
    }

    private static CriteriaBuilder getCriteriaBuilder() {
        // https://hibernate.zulipchat.com/#narrow/stream/132096-hibernate-user/topic/New.20functions.20in.20JPA.203.2E1/near/289429903
        // hibernate 6.1.x final 버전까지 jpa3.0에 의존하고 있기 때문에 HibernateCriteriaBuilder 로 형변환 해야한다.
        // 아직 final은 아니지만 hibernate 6.2.x CR 버전에서는 jpa 3.1에 의존하고 있으며 그냥 이 코드로 사용 가능하다. (형변환이 필요 없다.)
        return instance.getEntityManager().getCriteriaBuilder();
    }

    public static <T> List<T> listAll(Class<T> rootClass) {
        return list(rootClass).stream().map(i -> (T) i.get(0)).collect(Collectors.toList());
    }

    public static List<Tuple> list(Class<?> rootClass, SelectHelper... selectHelper) {
        var cb = getCriteriaBuilder();
        var query = cb.createTupleQuery();
        var root = query.from(rootClass);

        var list = new ArrayList<Selection<?>>();
        for (SelectHelper helper : selectHelper) {
            list.add(helper.apply(cb, root));
        }

        if (!list.isEmpty()) {
            query.multiselect(list);
        }

        return instance.getEntityManager().createQuery(query).getResultList();
    }

    public static List<?> list(String query) {
        return instance.getEntityManager().createQuery(query).getResultList();
    }
}
