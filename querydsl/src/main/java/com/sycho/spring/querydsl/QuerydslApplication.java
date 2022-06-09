package com.sycho.spring.querydsl;

import com.keencho.lib.custom.p6spy.CustomP6spyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = { "com.sycho.spring.* "},
        basePackageClasses = {CustomP6spyConfig.class}
)
public class QuerydslApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuerydslApplication.class, args);
    }

}
