import com.keencho.jpa31.HibernateHelper;
import com.keencho.jpa31.model.Book;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class JPA31Test {

    @BeforeAll
    public static void init() {
        // disable hibernate info log
        Logger logger = Logger.getLogger("org.hibernate");
        logger.setLevel(Level.OFF);

        HibernateHelper.beginTransaction();

        var book1 = new Book();
        book1.setTitle("Effective Java");
        book1.setPages(342);
        book1.setSellingRates(new BigDecimal("48.54"));
        book1.setClassificationSymbol(new BigDecimal("170.537"));
        book1.setReleaseDateTime(LocalDateTime.of(2020, 3, 13, 17, 20, 30));

        var book2 = new Book();
        book2.setTitle("Modern Java in Action");
        book2.setPages(573);
        book2.setSellingRates(new BigDecimal("51.46"));
        book2.setClassificationSymbol(new BigDecimal("2096.331"));
        book2.setReleaseDateTime(LocalDateTime.of(2022, 8, 13, 10, 43, 0));

        HibernateHelper.persist(book1);
        HibernateHelper.persist(book2);

        HibernateHelper.commit();
    }

    @AfterAll
    public static void close() {
        HibernateHelper.close();
    }

    static final String UUID_PATTERN = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    @Test
    @DisplayName("GenerationType.UUID test")
    void testUUID() {
        var resultList = HibernateHelper.listAll(Book.class);

        resultList.forEach(book -> {
            var id = book.getId().toString();

            System.out.println("uuid: " + id);
            Assertions.assertTrue(Pattern.matches(UUID_PATTERN, id));
        });
    }

    @Test
    @DisplayName("new numeric functions test")
    void testNewNumericFunctions() {
        var listAll = HibernateHelper.listAll(Book.class);

        var resultList = HibernateHelper.list(
                Book.class,
                // 수 올림
                (cb, root) -> cb.ceiling(root.get("sellingRates")),
                // 수 내림
                (cb, root) -> cb.floor(root.get("sellingRates")),
                // n번째 자리 반올림
                (cb, root) -> cb.round(root.get("sellingRates"), 1),
                // x를 인수로 하는 e^x 값을 반환
                (cb, root) -> cb.exp(root.get("sellingRates")),
                // 자연 로그를 반환
                (cb, root) -> cb.ln(root.get("sellingRates")),
                // n만큼 거듭제곱하여 반환
                (cb, root) -> cb.power(root.get("sellingRates"), 2),
                // 인수의 부호를 반환 (-1, 0, 1)
                (cb, root) -> cb.sign(root.get("sellingRates"))
        );

        System.out.println("\n\n");

        for (var i = 0; i < listAll.size(); i ++) {
            System.out.println("==========================");
            System.out.printf("원래 값: %s%n", listAll.get(i).getSellingRates().toString());
            System.out.printf("ceiling 함수: %s%n", resultList.get(i).get(0));
            System.out.printf("floor 함수: %s%n", resultList.get(i).get(1));
            System.out.printf("round 함수: %s%n", resultList.get(i).get(2));
            System.out.printf("exp 함수: %s%n", resultList.get(i).get(3));
            System.out.printf("ln 함수: %s%n", resultList.get(i).get(4));
            System.out.printf("power 함수: %s%n", resultList.get(i).get(5));
            System.out.printf("sign 함수: %s%n", resultList.get(i).get(6));
        }

        System.out.println("\n\n");
    }

    @Test
    @DisplayName("new DateTime functions test")
    void testNewDateTimeFunctions() {
        var now = LocalDateTime.now();
        var firstResult = HibernateHelper.list(
                Book.class,
                // 현재 시간
                (cb, root) -> cb.localTime(),
                // 현재 날짜
                (cb, root) -> cb.localDate(),
                // 현재 날짜 + 현재 시간
                (cb, root) -> cb.localDateTime()
        ).get(0);

        System.out.println("\n\n");

        System.out.println("현재시간: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("시간: " + firstResult.get(0));
        System.out.println("날짜: " + firstResult.get(1));
        System.out.println("날짜 + 시간: " + firstResult.get(2));

        System.out.println("\n\n");
    }

    @Test
    @DisplayName("new extract functions test")
    void textExtractFunctions() {
        // https://github.com/jakartaee/persistence/pull/356
        // criteria api에는 extract 함수가 존재하지 않는다 흑흑

        var query = """
                SELECT 
                    b.releaseDateTime as releaseDateTime,
                    EXTRACT(YEAR from b.releaseDateTime) as year,
                    EXTRACT(MONTH from b.releaseDateTime) as month,
                    EXTRACT(DAY from b.releaseDateTime) as day,
                    EXTRACT(HOUR from b.releaseDateTime) as hour,
                    EXTRACT(MINUTE from b.releaseDateTime) as minute,
                    EXTRACT(SECOND from b.releaseDateTime) as second
                FROM Book b
                """;

        System.out.println("\n\n");

        var list = HibernateHelper.list(query);

        for (var i = 0; i < list.size(); i ++) {
            var result = (Object[]) list.get(i);
            System.out.println("==========================");
            System.out.println("오리지널 값 (발매일): " + result[0]);
            System.out.println("년도: " + result[1]);
            System.out.println("달: " + result[2]);
            System.out.println("날짜: " + result[3]);
            System.out.println("시: " + result[4]);
            System.out.println("분: " + result[5]);
            System.out.println("초: " + result[6]);
        }

        System.out.println("\n\n");

    }

//    @Test
//    @DisplayName("new criteria case expressions")
//    void testCriteriaCaseExpressions() {
//        var now = LocalDateTime.now();
//        var firstResult = HibernateHelper.list(
//                Book.class,
//                (cb, root) -> cb.selectCase(root.get("title"))
//                        .when(root.get("title"), "df")
//        );
//    }
}
