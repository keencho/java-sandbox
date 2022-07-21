package com.sycho.spring.querydsl.repository.cross_join;

import com.sycho.spring.querydsl.model.cross_join.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
