package com.sycho.security.core.service;

import com.sycho.security.core.AccountResolver;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DefaultUserDetailsService<ACCOUNT, USER> extends UserDetailsService, AccountResolver<ACCOUNT, USER> {
}
