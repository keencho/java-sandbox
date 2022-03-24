package com.keencho.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
        var a= new ReentrantLock(true);

        a.lock();

        SpringApplication.run(WebfluxApplication.class, args);
    }

}
