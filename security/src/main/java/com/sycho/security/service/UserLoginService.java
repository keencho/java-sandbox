package com.sycho.security.service;

import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.service.KcDefaultLoginService;
import com.sycho.security.manager.UserLoginManager;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService extends KcDefaultLoginService<UserAccount, UserAccountRepository, Long, LoginAccountData> {

    public UserLoginService(
            KcAuthenticationProviderManager authenticationProviderManager,
            UserLoginManager accountLoginManager) {
        super(authenticationProviderManager, accountLoginManager);
    }

    @Override
    public Class<UserAccount> getAccountEntityClass() {
        return UserAccount.class;
    }
}
