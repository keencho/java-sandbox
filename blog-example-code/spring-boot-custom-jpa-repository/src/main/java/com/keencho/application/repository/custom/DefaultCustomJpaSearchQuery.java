package com.keencho.application.repository.custom;

import com.keencho.application.utils.QueryHandler;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.querydsl.QSort;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DefaultCustomJpaSearchQuery<T> implements CustomJpaSearchQuery<T> {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;
    private final EntityPath<T> path;

    // querydsl-apt 에 의해 생성된 q 클래스가 존재할 경우 -> path로 q 클래스 사용
    public DefaultCustomJpaSearchQuery(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.path = SimpleEntityPathResolver.INSTANCE.createPath(entityInformation.getJavaType());
    }

    // 생성된 q 클래스가 없거나 이를 사용할 수 없느 경우 (ex. unit test) - 새로운 entity path 생성
    public DefaultCustomJpaSearchQuery(Class<T> type, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.path = new EntityPathBase<>(type, "entity");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JPAQuery<?> buildWithoutSelect(Predicate predicate, Map<String, Expression<?>> bindings, QueryHandler queryHandler, Sort sort) {
        var query = queryFactory.from(path);

        if (predicate != null) {
            query = query.where(predicate);
        }

        if (queryHandler != null) {
            query = queryHandler.apply(query);
        }

        if (sort != null) {
            // query dsl의 sort 객체인 경우와 아닌 경우 구분
            if (sort instanceof QSort qSort) {
                query = query.orderBy(qSort.getOrderSpecifiers().toArray(new OrderSpecifier[0]));
            } else {

                // sort null handling을 query dsl의 null handling으로 캐스팅
                Function<Sort.NullHandling, OrderSpecifier.NullHandling> castToQueryDSL = nullHandling -> switch(nullHandling) {
                    case NULLS_FIRST -> OrderSpecifier.NullHandling.NullsFirst;
                    case NULLS_LAST -> OrderSpecifier.NullHandling.NullsLast;
                    default -> OrderSpecifier.NullHandling.Default;
                };

                for (var order : sort) {
                    var queryDSLNullHandling = castToQueryDSL.apply(order.getNullHandling());

                    var orderBy = order.isAscending() ? Order.ASC : Order.DESC;
                    OrderSpecifier<?> os;

                    // 바인딩이 프로퍼티 (컬럼명) 을 포함하고 있는 경우
                    if (bindings != null && bindings.containsKey(order.getProperty())) {
                        var expression = bindings.get(order.getProperty());

                        // 별칭 (.as("...")) 으로 별칭을 지정했다면 origianl column명을 가져와 Path Expression을 생성하여 정렬한다.
                        if (expression instanceof Operation && ((Operation<?>) expression).getOperator() == Ops.ALIAS) {
                            os = new OrderSpecifier<>(orderBy, Expressions.stringPath(( (Operation<?>) expression ).getArg(1).toString() ), queryDSLNullHandling);
                        }
                        // 별칭을 쓰지 않았다면 그대로 사용하면 된다.
                        else {
                            os = new OrderSpecifier(orderBy, expression, queryDSLNullHandling);
                        }
                    }
                    // 포함하고 있지 않다면 stringPath를 생성하여 정렬한다.
                    else {
                        os = new OrderSpecifier<>(orderBy, Expressions.stringPath(order.getProperty()), queryDSLNullHandling);
                    }

                    query = query.orderBy(os);
                }
            }
        }

        return query;
    }

    @Override
    public List<T> findList(Predicate predicate, QueryHandler queryHandler, Sort sort) {
        return this.buildWithoutSelect(predicate, null, queryHandler, sort).select(path).fetch();
    }

    @Override
    public Page<T> findPage(Predicate predicate, QueryHandler queryHandler, Pageable pageable) {
        Assert.notNull(pageable, "pageable must not be null!");

        var query = this.buildWithoutSelect(predicate, null, queryHandler, pageable.getSort()).select(path);

        var totalSize = query.fetch().size();

        query = query.offset(pageable.getOffset()).limit(pageable.getPageSize());

        return new PageImpl<>(query.select(path).fetch(), pageable, totalSize);
    }

    @Override
    public <P> List<P> selectList(Predicate predicate, Class<P> type, Map<String, Expression<?>> bindings, QueryHandler queryHandler, Sort sort) {
        return this.buildWithoutSelect(predicate, bindings, queryHandler, sort).select(Projections.bean(type, bindings)).fetch();
    }

    @Override
    public <P> Page<P> selectPage(Predicate predicate, Class<P> type, Map<String, Expression<?>> bindings, QueryHandler queryHandler, Pageable pageable) {
        Assert.notNull(pageable, "pageable must not be null!");

        var query = this.buildWithoutSelect(predicate, bindings, queryHandler, pageable.getSort()).select(path);

        var totalSize = query.fetch().size();

        query = query.offset(pageable.getOffset()).limit(pageable.getPageSize());

        return new PageImpl<>(query.select(Projections.bean(type, bindings)).fetch(), pageable, totalSize);
    }
}
