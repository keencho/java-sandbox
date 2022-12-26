import com.keencho.jpa31.HibernateHelper;
import com.keencho.jpa31.model.Book;
import org.junit.jupiter.api.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class JPA31Test {

    @BeforeEach
    public void init() {
        // disable hibernate info log
        Logger logger = Logger.getLogger("org.hibernate");
        logger.setLevel(Level.OFF);

        HibernateHelper.init();
    }

    @AfterEach
    public void close() {
        HibernateHelper.close();
    }

    static final String UUID_PATTERN = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    @Test
    @DisplayName("GenerationType.UUID test")
    void test1() {
        /**
         * 기존
         *
         * @Id
         * @GeneratedValue(generator = "UUID")
         * @GenericGenerator(
         *  name = "UUID",
         *  strategy = "org.hibernate.id.UUIDGenerator",
         * )
         * private UUID id;
         */
        var book = new Book("이펙티브 자바", 356);
        HibernateHelper.persist(book);

        assertTrue(Pattern.matches(UUID_PATTERN, book.getId().toString()));

    }
}
