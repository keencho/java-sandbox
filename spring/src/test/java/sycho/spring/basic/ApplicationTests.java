package sycho.spring.basic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sycho.spring.basic.utils.ReflectionUtils;

@SpringBootTest
class ApplicationTests {

    @Test
    void reflectionTest() {
        var a = ReflectionUtils.findEnumClasses("sycho.spring.basic");
    }

}
