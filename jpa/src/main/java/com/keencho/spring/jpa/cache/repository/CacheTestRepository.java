package com.keencho.spring.jpa.cache.repository;

import com.keencho.spring.jpa.cache.model.CacheTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheTestRepository extends JpaRepository<CacheTestModel, Long> {
}
