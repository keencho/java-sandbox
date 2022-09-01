package com.sycho.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(
//        basePackages = { "com.sycho.security" },
//        basePackageClasses = {CustomP6spyConfig.class}
//)
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
