package com.keencho.application;

import com.keencho.application.repository.custom.CustomJpaQueryDSLRepositoryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomJpaQueryDSLRepositoryFactory.class)
public class SpringBootCustomJpaRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCustomJpaRepositoryApplication.class, args);
    }

}
