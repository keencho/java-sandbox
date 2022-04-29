package com.sycho.spring.querydsl.repository;

import com.sycho.spring.querydsl.model.MainOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainOrderRepository extends JpaRepository<MainOrder, Long> {
}
