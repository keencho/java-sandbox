package com.sycho.security.repository;

import com.keencho.lib.spring.security.repository.KcAccountRepository;
import com.sycho.security.model.AdminAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAccountRepository extends KcAccountRepository<AdminAccount, Long> {
}
