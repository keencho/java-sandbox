package com.sycho.security.core;

import com.sycho.security.core.model.SecurityUser;

import java.lang.reflect.Type;

public interface AccountResolver<ACCOUNT, USER> {

    Type getAccountClass();

    Type getUserClass();

    ACCOUNT getAccount(SecurityUser securityUser);

    USER getUser(SecurityUser securityUser);
}
