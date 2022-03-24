package com.keencho.spring.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebfluxApplicationTests {

    String a = null;

    @Test
    void contextLoads() {

        synchronized (this) {
            this.a = "dma.";
        }
    }

}
