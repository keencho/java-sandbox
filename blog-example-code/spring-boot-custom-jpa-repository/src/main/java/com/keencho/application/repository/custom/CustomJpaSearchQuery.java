package com.keencho.application.repository.custom;

import com.keencho.application.utils.QueryHandler;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface CustomJpaSearchQuery<T> {

    List<T> findList(Predicate predicate, QueryHandler queryHandler, Sort sort);

    Page<T> findPage(Predicate predicate, QueryHandler queryHandler, Pageable pageable);

    <P> List<P> selectList(Predicate predicate, Class<P> type, Map<String, Expression<?>> bindings, QueryHandler queryHandler, Sort sort);

    <P> Page<P> selectPage(Predicate predicate, Class<P> type, Map<String, Expression<?>> bindings, QueryHandler queryHandler, Pageable pageable);

}
