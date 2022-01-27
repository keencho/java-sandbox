package com.keencho.spring.jpa.osiv.repository;

import com.keencho.spring.jpa.osiv.model.OSIVParentTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSIVParentTestRepository extends JpaRepository<OSIVParentTestModel, Long> {
}
