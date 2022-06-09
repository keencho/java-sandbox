package com.sycho.spring.querydsl.repository;

import com.sycho.spring.querydsl.model.SubOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubOrderRepository extends JpaRepository<SubOrder, Long> {
}
