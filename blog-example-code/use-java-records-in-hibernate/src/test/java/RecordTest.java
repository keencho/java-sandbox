import com.keencho.model.Order;
import com.keencho.model.OrderRecord;
import com.keencho.model.OrderStatus;
import com.keencho.model.Order_;
import com.keencho.model.ShippingInfo;
import com.keencho.utils.DataGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class RecordTest {

    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;

    static List<OrderStatus> orderStatusList = Arrays.asList(OrderStatus.values());
    static Random rand = new Random();
    static int rowNum = 500;

    private static Order generateOrder() {
        Order order = new Order();

        order.setStatus(orderStatusList.get(rand.nextInt(orderStatusList.size())));

        order.setFromInfo(new ShippingInfo(DataGenerator.name(), DataGenerator.address(), DataGenerator.phone()));
        order.setToInfo(new ShippingInfo(DataGenerator.name(), DataGenerator.address(), DataGenerator.phone()));

        order.setItemName(DataGenerator.itemName());
        order.setItemPrice(Integer.parseInt(rand.nextInt(10, 100) + "0000"));
        order.setCreatedDateTime(LocalDateTime.of(2023, 4, rand.nextInt(1,31), rand.nextInt(24), rand.nextInt(60), rand.nextInt(60)));

        return order;
    }

    @BeforeAll
    public static void beforeAll() {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        IntStream.range(0, rowNum).forEach(idx -> entityManager.persist(generateOrder()));

        entityManager.getTransaction().commit();
    }

    @AfterAll
    public static void afterAll() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void recordDTO() {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(OrderRecord.class);
        var root = cq.from(Order.class);

        cq.select(
                cb.construct(
                    OrderRecord.class,
                    root.get(Order_.id),
                    root.get(Order_.status),
                    root.get("fromInfo"),
                    root.get("toInfo"),
                    root.get(Order_.itemName),
                    root.get(Order_.itemPrice),
                    root.get(Order_.createdDateTime)
                )
        );

        cq.where(cb.like(root.get("fromInfo").get("name"), "%김%"));

        var q = entityManager.createQuery(cq);
        var list = q.getResultList();
    }

}
