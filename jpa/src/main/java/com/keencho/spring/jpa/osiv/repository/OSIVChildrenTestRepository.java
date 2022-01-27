package com.keencho.spring.jpa.osiv.repository;

import com.keencho.spring.jpa.osiv.model.OSIVChildrenTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSIVChildrenTestRepository extends JpaRepository<OSIVChildrenTestModel, Long> {
}
