package com.keencho.spring.jpa.repo;

import com.keencho.spring.jpa.model.OSIVParentTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSIVParentTestRepository extends JpaRepository<OSIVParentTestModel, Long> {
}
