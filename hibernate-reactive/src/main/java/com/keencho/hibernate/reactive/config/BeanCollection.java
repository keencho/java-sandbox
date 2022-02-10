package com.keencho.hibernate.reactive.config;

import com.keencho.hibernate.reactive.repository.KcReactiveRepository;
import com.keencho.hibernate.reactive.repository.KcReactiveRepositoryImpl;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Persistence;

@Configuration
public class BeanCollection {

    @Bean
    public KcReactiveRepository genericRepository() {
        return new KcReactiveRepositoryImpl(
                Persistence
                        .createEntityManagerFactory("test")
                        .unwrap(Mutiny.SessionFactory.class)
        );
    }

}
