package com.sycho.security.service;

import com.keencho.lib.spring.security.model.KcAuthReturnType;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.provider.KcJwtTokenProvider;
import com.keencho.lib.spring.security.service.KcDefaultLoginService;
import com.sycho.security.manager.UserLoginManager;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService extends KcDefaultLoginService<UserAccount, UserAccountRepository, Long, LoginAccountData> {

    public UserLoginService(
            KcAuthenticationProviderManager authenticationProviderManager,
            UserLoginManager accountLoginManager
    ) {
        super(authenticationProviderManager, accountLoginManager, null);
    }

    @Override
    public Class<UserAccount> getAccountEntityClass() {
        return UserAccount.class;
    }

    @Override
    public KcAuthReturnType getAuthReturnType() {
        return KcAuthReturnType.AUTHENTICATION;
    }
}
