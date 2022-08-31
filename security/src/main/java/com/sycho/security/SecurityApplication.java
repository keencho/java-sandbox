package com.sycho.security;

import com.keencho.lib.custom.p6spy.CustomP6spyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = { "com.sycho.security" },
        basePackageClasses = {CustomP6spyConfig.class}
)
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
