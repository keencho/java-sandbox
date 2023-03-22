import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keencho.dto.QOrderAggregationDTO;
import com.keencho.model.Order;
import com.keencho.model.QOrder;
import com.keencho.model.ShippingInfo;
import com.keencho.utils.DataGenerator;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class JsonbTest {

    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;

    private static void generateOrder() {
        var order = new Order();

        var from = new ShippingInfo();
        from.setName(DataGenerator.name());
        from.setNumber(DataGenerator.phone());
        from.setAddress(DataGenerator.address());

        var to = new ShippingInfo();
        to.setName(DataGenerator.name());
        to.setNumber(DataGenerator.phone());
        to.setAddress(DataGenerator.address());

        order.setFromInfo(from);
        order.setToInfo(to);

        entityManager.persist(order);
    }

    private static void doInTransaction(Runnable task) {
        entityManager.getTransaction().begin();

        task.run();

        entityManager.getTransaction().commit();
    }

    private JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @BeforeAll
    public static void beforeAll() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        entityManager = entityManagerFactory.createEntityManager();

        doInTransaction(() -> {
            var list = entityManager.createQuery("SELECT o FROM Order o").getResultList();

            if (list.isEmpty()) {
                IntStream.range(0, 100).forEach((idx) -> generateOrder());
            }
        });
    }

    @Test
    @DisplayName("JSONB 컬럼 특정 필드 업데이트")
    public void jsonbUpdateTest() {

        doInTransaction(() -> {
            var order = entityManager.find(Order.class, 1L);
            order.getFromInfo().setNumber("01000001111");
        });
    }

    @Test
    @DisplayName("NATIVE QUERY JSONB 조건 테스트 (version < 6.2)")
    public void jsonbNativeQueryCondition() {
        var mapper = new ObjectMapper();

        var orderList = entityManager
                .createNativeQuery("SELECT * FROM order_new o WHERE o.frominfo ->> 'name' LIKE :name ")
                .setParameter("name", "김%")
                .getResultList();

        orderList.forEach(item -> {
            try {
                var fromInfo = mapper.readValue(((Object[]) item)[1].toString(), ShippingInfo.class);

                Assertions.assertTrue(fromInfo.getName().startsWith("김"));

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    @DisplayName("JPQL JSONB 조건 테스트 (version >= 6.2")
    public void jsonbJPQLCondition() {
        var orderList = entityManager
                .createQuery("SELECT o FROM Order o WHERE o.fromInfo.name LIKE :name", Order.class)
                .setParameter("name", "김%")
                .getResultList();

        Assertions.assertTrue(orderList.stream().allMatch(o -> o.getFromInfo().getName().startsWith("김")));
    }

    @Test
    @DisplayName("QueryDSL JSONB 조건 테스트")
    public void jsonbQueryDSLCondition() {
        var q = QOrder.order;

        var lastName = q.fromInfo.name.substring(0, 1);
        var count = q.count();

        var list = jpaQueryFactory()
                .select(new QOrderAggregationDTO(lastName, count))
                .from(q)
                .where(q.fromInfo.name.startsWith("김").not())
                .groupBy(lastName)
                .orderBy(count.desc())
                .fetch();

        list.forEach(item -> Assertions.assertFalse(item.getLastName().startsWith("김")));

    }
}
