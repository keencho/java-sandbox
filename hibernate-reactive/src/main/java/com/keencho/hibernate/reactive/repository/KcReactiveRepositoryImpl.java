package com.keencho.hibernate.reactive.repository;

import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Arrays;
import java.util.List;

@Component
public record KcReactiveRepositoryImpl(Mutiny.SessionFactory sessionFactory) implements KcReactiveRepository {

    @Override
    public <E> Uni<E> save(E entity) {
        Assert.notNull(entity, "entity must not be null!");

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
        Assert.notNull(id, "id must not be null!");

        return sessionFactory.withSession(session -> session.find(clazz, id));
    }

    @Override
    public <E> Uni<Integer> deleteById(Class<E> clazz, Object id) {
        Assert.notNull(id, "id must not be null!");

        var criteriaBuilder = this.sessionFactory.getCriteriaBuilder();

        CriteriaDelete<E> delete = criteriaBuilder.createCriteriaDelete(clazz);
        var root = delete.from(clazz);
        var idField = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class) != null)
                .findFirst().orElse(null);

        Assert.notNull(idField, "id field of entity must not be null!");

        delete.where(criteriaBuilder.equal(root.get(idField.getName()), id));

        return sessionFactory.withTransaction((session, tx) -> session.createQuery(delete).executeUpdate());

    }

    @Override
    public <E> Uni<Integer> deleteAll(Class<E> clazz) {
        Assert.notNull(clazz, "class not not be null!");

        var criteriaBuilder = this.sessionFactory.getCriteriaBuilder();

        CriteriaDelete<E> delete = criteriaBuilder.createCriteriaDelete(clazz);
        delete.from(clazz);

        return sessionFactory.withTransaction((session, tx) -> session.createQuery(delete).executeUpdate());
    }

    private <E> CriteriaQuery<E> buildQuery(Class<E> clazz) {
        return sessionFactory.getCriteriaBuilder().createQuery(clazz);
    }
}
