package com.keencho.jooq;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@org.springframework.context.annotation.Configuration
public class JOOQSpringBootApplication {

    @Bean
    public DefaultConfigurationCustomizer configurationCustomizer() {
        return (DefaultConfiguration c) -> c.settings()
                .withRenderFormatted(true);
    }

    public static void main(String[] args) {
        SpringApplication.run(JOOQSpringBootApplication.class, args);
    }

}
