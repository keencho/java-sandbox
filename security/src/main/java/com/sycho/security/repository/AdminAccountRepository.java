package com.sycho.security.repository;

import com.sycho.security.model.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {
}
