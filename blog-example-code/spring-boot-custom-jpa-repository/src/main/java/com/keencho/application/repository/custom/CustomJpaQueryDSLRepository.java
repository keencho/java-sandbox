package com.keencho.application.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaQueryDSLRepository<T, ID> extends JpaRepository<T, ID>, CustomJpaSearchQuery<T> {
}
