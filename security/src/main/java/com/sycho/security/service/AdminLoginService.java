package com.sycho.security.service;

import com.keencho.lib.spring.security.model.KcAuthReturnType;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.provider.KcJwtTokenProvider;
import com.keencho.lib.spring.security.service.KcDefaultLoginService;
import com.sycho.security.manager.AdminLoginManager;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.repository.AdminAccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService extends KcDefaultLoginService<AdminAccount, AdminAccountRepository, Long, String> {
    public AdminLoginService(
            KcAuthenticationProviderManager authenticationProviderManager,
            AdminLoginManager accountLoginManager,
            @Qualifier("adminJwtTokenProvider")
            KcJwtTokenProvider jwtTokenProvider
    ) {
        super(authenticationProviderManager, accountLoginManager, jwtTokenProvider);
    }

    @Override
    public Class<AdminAccount> getAccountEntityClass() {
        return AdminAccount.class;
    }

    @Override
    public KcAuthReturnType getAuthReturnType() {
        return KcAuthReturnType.JWT_TOKEN;
    }
}
