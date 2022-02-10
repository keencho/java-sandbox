package com.keencho.hibernate.reactive.repository;

import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public record KcReactiveRepositoryImpl(Mutiny.SessionFactory sessionFactory) implements KcReactiveRepository {

    @Override
    public <E> Uni<E> save(E entity) {
        return sessionFactory.withSession(session -> session.persist(entity).chain(session::flush).replaceWith(entity));
    }

    @Override
    public <E> Uni<List<E>> listAll(Class<E> clazz) {
        var query = this.buildQuery(clazz);

        query.from(clazz);

        return sessionFactory.withSession(session -> session.createQuery(query).getResultList());
    }

    @Override
    public <E> Uni<E> findById(Class<E> clazz, Object id) {
        var query = this.buildQuery(clazz);

        return sessionFactory.withSession(session -> session.find(clazz, id));
    }

    private <E> CriteriaQuery<E> buildQuery(Class<E> clazz) {
        return sessionFactory.getCriteriaBuilder().createQuery(clazz);
    }
}
