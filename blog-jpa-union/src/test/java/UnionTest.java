import com.keencho.model.*;
import com.keencho.utils.DataGenerator;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.PostgreSQLTemplates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Selection;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaRoot;
import org.hibernate.query.criteria.JpaSubQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UnionTest {

    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;
    static HibernateCriteriaBuilder hibernateCriteriaBuilder;

    static List<OrderStatus> orderStatusList = Arrays.asList(OrderStatus.values());
    static Random rand = new Random();
    static int rowNum = 500;

    private static <T extends Order> T generateOrder(int year, int month, Class<T> targetClass) {
        T order;
        try {
            order = targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("class initialization failed ");
        }

        order.setStatus(orderStatusList.get(rand.nextInt(orderStatusList.size())));

        order.setFromAddress(DataGenerator.address());
        order.setFromName(DataGenerator.name());
        order.setFromPhoneNumber(DataGenerator.phone());

        order.setToAddress(DataGenerator.address());
        order.setToName(DataGenerator.name());
        order.setToPhoneNumber(DataGenerator.phone());

        order.setItemName(DataGenerator.itemName());
        order.setItemPrice(Integer.parseInt(rand.nextInt(10, 100) + "0000"));
        order.setCreatedDateTime(LocalDateTime.of(year, month, rand.nextInt(1,31), rand.nextInt(24), rand.nextInt(60), rand.nextInt(60)));

        return order;
    }

    @BeforeAll
    public static void beforeAll() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        entityManager = entityManagerFactory.createEntityManager();
        hibernateCriteriaBuilder = entityManager.unwrap(Session.class).getCriteriaBuilder();

        entityManager.getTransaction().begin();

        IntStream.range(0, rowNum).forEach(idx -> entityManager.persist(generateOrder(2022, 6, Order_2206.class)));
        IntStream.range(0, rowNum).forEach(idx -> entityManager.persist(generateOrder(2022, 9, Order_2209.class)));
        IntStream.range(0, rowNum).forEach(idx -> entityManager.persist(generateOrder(2023, 1, Order_2301.class)));

        entityManager.getTransaction().commit();
    }

    private static JPASQLQuery<?> query() {
        return new JPASQLQuery<>(entityManager, new PostgreSQLTemplates());
    }

    @AfterAll
    public static void afterAll() {
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("Hibernate HQL UNION ALL")
    void hibernateHQL() {
        // o.id AS id, o.status AS status, o.fromAddress AS fromAddress, o.fromName AS fromName, o.fromPhoneNumber AS fromPhoneNumber, o.toAddress AS toAddress, o.toName AS toName, o.toPhoneNumber AS toPhoneNumber, o.itemName AS itemName, o.itemPrice AS itemPrice, o.createdDateTime AS createdDateTime
        var fieldsWithAlias = Arrays.stream(Order.class.getDeclaredFields()).map(i -> "o." + i.getName() + " AS " + i.getName()).collect(Collectors.joining(", "));

        var query = String.format("""
                SELECT %1$s
                FROM (
                    SELECT %1$s
                    FROM Order_2206 AS o
                    WHERE o.toName LIKE :name
                    UNION ALL
                    SELECT %1$s
                    FROM Order_2209 AS o
                    UNION ALL
                    SELECT %1$s
                    FROM Order_2301 AS o
                    WHERE o.itemPrice >= :price
                ) AS o
                WHERE o.status <> :status
                """, fieldsWithAlias);

        var list = entityManager
                .createQuery(query)
                .setParameter("name", "김%")
                .setParameter("price", 100000)
                .setParameter("status", OrderStatus.FAILED)
                .getResultList();

        System.out.println(list);
    }

    @Test
    @DisplayName("Hibernate Criteria API UNION ALL")
    void hibernateCriteriaAPI() {
        Function<JpaRoot<?>, Selection<?>[]> rootPath = (root) -> Arrays.stream(Order.class.getDeclaredFields()).map(field -> root.get(field.getName()).alias(field.getName())).toArray(Selection[]::new);

        var scq = hibernateCriteriaBuilder.createQuery(Order.class);
        BiFunction<Class<? extends Order>, Function<JpaRoot<? extends Order>, Expression<Boolean>>, JpaSubQuery<Tuple>> jpaCriteriaQuery = (clazz, condition) -> {
            var sq = scq.subquery(Tuple.class);
            var root = sq.from(clazz);

            sq.multiselect(rootPath.apply(root));

            if (condition != null) {
                sq.where(condition.apply(root));
            }

            return sq;
        };

        var sq1 = jpaCriteriaQuery.apply(Order_2206.class, (root) -> hibernateCriteriaBuilder.ilike(root.get("toName"), "김%"));
        var sq2 = jpaCriteriaQuery.apply(Order_2209.class, null);
        var sq3 = jpaCriteriaQuery.apply(Order_2301.class, (root) -> hibernateCriteriaBuilder.ge(root.get("itemPrice"), 100000));

        var cq = hibernateCriteriaBuilder.createQuery(Order.class);
        var root = cq.from(hibernateCriteriaBuilder.unionAll(sq1, sq2, sq3));

        cq.multiselect(rootPath.apply(root));
        cq.where(hibernateCriteriaBuilder.notEqual(root.get("status"), OrderStatus.FAILED));

        var list = entityManager.createQuery(cq).getResultList();

        System.out.println(list);
    }

    @Test
    @DisplayName("QueryDSL UNION ALL")
    void queryDSL() {
        var q = QOrder.order;
        var q1 = QOrder_2206.order_2206;
        var q2 = QOrder_2209.order_2209;
        var q3 = QOrder_2301.order_2301;

        var unionList = query().select(q.toName, q.toAddress, q.itemName, q.itemPrice).from(
                query().unionAll(
                        query()
                                .select(q1.toName.as(q.toName), q1.toAddress.as(q.toAddress), q1.itemName.as(q.itemName), q1.itemPrice.as(q.itemPrice))
                                .from(q1)
                                .where(q1.toName.like("김%"))
                        ,
                        query()
                                .select(q2.toName.as(q.toName), q2.toAddress.as(q.toAddress), q2.itemName.as(q.itemName), q2.itemPrice.as(q.itemPrice))
                                .from(q2),
                        query()
                                .select(q3.toName.as(q.toName), q3.toAddress.as(q.toAddress), q3.itemName.as(q.itemName), q3.itemPrice.as(q.itemPrice))
                                .from(q3)
                                .where(q3.itemPrice.goe(100000))
                ).as(String.valueOf(q))
        ).fetch();

        System.out.println(unionList);
    }
}
