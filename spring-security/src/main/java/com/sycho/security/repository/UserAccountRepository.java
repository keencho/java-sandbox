package com.sycho.security.repository;

import com.keencho.lib.spring.security.repository.KcAccountRepository;
import com.sycho.security.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends KcAccountRepository<UserAccount, Long> {
}
