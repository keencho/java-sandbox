package com.keencho.hibernate.reactive.config;

import com.keencho.hibernate.reactive.repository.KcReactiveRepository;
import com.keencho.hibernate.reactive.repository.KcReactiveRepositoryImpl;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Persistence;

@Configuration
public class Config {

    @Bean
    public Mutiny.SessionFactory sessionFactory() {
        return Persistence
                .createEntityManagerFactory("pu")
                .unwrap(Mutiny.SessionFactory.class);
    }

    @Bean
    public KcReactiveRepository kcReactiveRepository() {
        return new KcReactiveRepositoryImpl(
                Persistence
                        .createEntityManagerFactory("pu")
                        .unwrap(Mutiny.SessionFactory.class)
        );
    }

}
