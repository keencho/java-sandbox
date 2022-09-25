package com.keencho.spring.jpa;

import com.keencho.lib.spring.jpa.querydsl.repository.KcJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        repositoryFactoryBeanClass = KcJpaRepositoryFactoryBean.class
)
public class KeenchoSpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeenchoSpringJpaApplication.class, args);
    }

}
