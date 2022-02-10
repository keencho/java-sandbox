package com.keencho.hibernate.reactive.repository;

import io.smallrye.mutiny.Uni;

import java.util.List;

public interface KcReactiveRepository {

    <E> Uni<E> save(E entity);

    <E> Uni<List<E>> listAll(Class<E> clazz);

    <E> Uni<E> findById(Class<E> clazz, Object id);
}
