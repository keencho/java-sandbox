import com.keencho.hibernate6.HibernateHelper;
import com.keencho.hibernate6.model.ColorOne;
import com.keencho.hibernate6.model.ColorTwo;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hibernate6Test {

    private static final String[] COLOR1 = new String[] { "BLUE", "RED", "YELLOW", "GREEN", "BLACK" };
    private static final String[] COLOR2 = new String[] { "PINK", "RED", "PURPLE", "GREEN", "WHITE", "ORANGE" };

    @BeforeAll
    public static void beforeAll() {
        // disable hibernate info log
        Logger logger = Logger.getLogger("org.hibernate");
        logger.setLevel(Level.OFF);

        HibernateHelper.beginTransaction();

        Arrays.stream(COLOR1).forEach(c -> HibernateHelper.persist(new ColorOne(c)));
        Arrays.stream(COLOR2).forEach(c -> HibernateHelper.persist(new ColorTwo(c)));

        HibernateHelper.commit();
    }

    @AfterAll
    public static void close() {
        HibernateHelper.close();
    }

    private final static String QUERY_BLOCK = """
            SELECT c1.name as name
            FROM ColorOne c1
            %s
            SELECT c2.name as name
            FROM ColorTwo c2
            """;

    @Test
    @DisplayName("Validate data integrity")
    void validate() {
        assertEquals(HibernateHelper.listAll(ColorOne.class).size(), COLOR1.length);
        assertEquals(HibernateHelper.listAll(ColorTwo.class).size(), COLOR2.length);
    }

    @Test
    @DisplayName("Hibernate6 UNION")
    void hibernate6Union() {
        var list = HibernateHelper.list(String.format(QUERY_BLOCK, "UNION"));

        var colorList = new ArrayList<>(List.of(COLOR1));
        colorList.addAll(List.of(COLOR2));
        assertEquals(list.size(), colorList.stream().distinct().toList().size());
    }

    @Test
    @DisplayName("Hibernate6 UNION ALL")
    void hibernate6UnionAll() {
        var list = HibernateHelper.list(String.format(QUERY_BLOCK, "UNION ALL"));

        Assertions.assertEquals(list.size(), COLOR1.length + COLOR2.length);
    }

    @Test
    @DisplayName("Hibernate6 INTERSECT")
    void hibernate6Intersect() {
        var list = HibernateHelper.list(String.format(QUERY_BLOCK, "INTERSECT"));

        var colorList = new ArrayList<>(List.of(COLOR1));
        colorList.addAll(List.of(COLOR2));
        assertEquals(list.size(), colorList.stream().filter(color -> Collections.frequency(colorList, color) > 1).collect(Collectors.toSet()).size());
    }

    @Test
    @DisplayName("Hibernate6 EXCEPT")
    void hibernate6Except() {
        var list = HibernateHelper.list(String.format(QUERY_BLOCK, "EXCEPT"));

        // ColorTwo에는 없고 ColorOne에만 존재하는 이름
        assertEquals(list.size(), Arrays.stream(COLOR1).filter(color1 -> !Arrays.asList(COLOR2).contains(color1)).toList().size());
    }
}
