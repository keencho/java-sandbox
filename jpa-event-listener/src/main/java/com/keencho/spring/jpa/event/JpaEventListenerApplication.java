package com.keencho.spring.jpa.event;

import com.keencho.lib.custom.p6spy.CustomP6spyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = { "com.keencho.spring.jpa" },
        basePackageClasses = { CustomP6spyConfig.class }
)
public class JpaEventListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEventListenerApplication.class, args);
    }

}
