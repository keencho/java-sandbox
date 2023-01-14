import com.keencho.querydsl.JPAHelper;
import com.keencho.querydsl.model.*;
import com.keencho.querydsl.util.DataGenerator;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static com.keencho.querydsl.JPAHelper.Q.query;
import static org.junit.jupiter.api.Assertions.*;
import static com.keencho.querydsl.JPAHelper.Q.*;

public class QueryDSLTest {

    static JPAQueryFactory queryFactory = JPAHelper.INSTANCE.getQueryFactory();
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
        order.setItemPrice(Integer.parseInt(rand.nextInt(100) + "0000"));
        order.setCreatedDateTime(LocalDateTime.of(year, month, rand.nextInt(1,31), rand.nextInt(24), rand.nextInt(60), rand.nextInt(60)));

        return order;
    }

    @BeforeAll
    public static void beforeAll() {
        JPAHelper.beginTransaction();

        IntStream.range(0, rowNum).forEach(idx -> JPAHelper.persist(generateOrder(2022, 6, Order_2206.class)));
        IntStream.range(0, rowNum).forEach(idx -> JPAHelper.persist(generateOrder(2022, 9, Order_2209.class)));
        IntStream.range(0, rowNum).forEach(idx -> JPAHelper.persist(generateOrder(2022, 11, Order_2211.class)));
        IntStream.range(0, rowNum).forEach(idx -> JPAHelper.persist(generateOrder(2022, 12, Order_2212.class)));
        IntStream.range(0, rowNum).forEach(idx -> JPAHelper.persist(generateOrder(2023, 1, Order_2301.class)));

        JPAHelper.commit();
    }

    @AfterAll
    public static void afterAll() {
        JPAHelper.close();
    }

    @Test
    @DisplayName("Validate Data")
    void validateData() {
        assertEquals(queryFactory.selectFrom(q1).fetch().size(), rowNum);
        assertEquals(queryFactory.selectFrom(q2).fetch().size(), rowNum);
        assertEquals(queryFactory.selectFrom(q3).fetch().size(), rowNum);
        assertEquals(queryFactory.selectFrom(q4).fetch().size(), rowNum);
        assertEquals(queryFactory.selectFrom(q5).fetch().size(), rowNum);
    }

    @Test
    @DisplayName("UNION")
    void union() {
        var unionList = query().union(
                query().select(q1.fromAddress).from(q1),
                query().select(q2.fromAddress).from(q2),
                query().select(q3.fromAddress).from(q3),
                query().select(q4.fromAddress).from(q4),
                query().select(q5.fromAddress).from(q5)
        ).fetch();

        var set = new HashSet<>();
        Function<JPAQuery<? extends Order>, Void> function = (order) -> {
            var list = order.fetch();
            list.forEach(item -> set.add(item.getFromAddress()));
            return null;
        };

        function.apply(queryFactory.selectFrom(q1));
        function.apply(queryFactory.selectFrom(q2));
        function.apply(queryFactory.selectFrom(q3));
        function.apply(queryFactory.selectFrom(q4));
        function.apply(queryFactory.selectFrom(q5));

        assertEquals(unionList.size(), set.size());
    }

    @Test
    @DisplayName("UNION ALL")
    void unionAll() {
        var unionAllIst = query().unionAll(
                query().select(q1.fromAddress).from(q1),
                query().select(q2.fromAddress).from(q2),
                query().select(q3.fromAddress).from(q3),
                query().select(q4.fromAddress).from(q4),
                query().select(q5.fromAddress).from(q5)
        ).fetch();

        var list = new ArrayList<>();
        Function<JPAQuery<? extends Order>, Void> function = (order) -> {
            var fetch = order.fetch();
            fetch.forEach(item -> list.add(item.getFromAddress()));
            return null;
        };

        function.apply(queryFactory.selectFrom(q1));
        function.apply(queryFactory.selectFrom(q2));
        function.apply(queryFactory.selectFrom(q3));
        function.apply(queryFactory.selectFrom(q4));
        function.apply(queryFactory.selectFrom(q5));

        assertEquals(unionAllIst.size(), list.size());
    }
}
