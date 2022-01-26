package com.keencho.spring.jpa.repo;

import com.keencho.spring.jpa.model.CacheTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheTestRepository extends JpaRepository<CacheTestModel, Long> {
}
