package com.keencho.spring.jpa.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

//    @Bean
//    public DataSource psqlDataSource() {
//        var builder = DataSourceBuilder.create();
//        builder.driverClassName("org.postgresql.Driver");
//        builder.url("jdbc:postgresql://localhost:5432/jpa");
//        builder.username("postgres");
//        builder.password("123qwe@@");
//
//        return builder.build();
//    }

    @Bean
    public DataSource h2DataSource() {
        var builder = DataSourceBuilder.create();
        builder.driverClassName("org.h2.Driver");
        builder.url("jdbc:h2:../test");
        builder.username("sa");
        builder.password("");

        return builder.build();
    }
}
