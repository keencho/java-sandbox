package com.sycho.security.service;

import com.keencho.lib.spring.security.manager.KcLoginManager;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.service.KcDefaultLoginService;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.repository.AdminAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService extends KcDefaultLoginService<AdminAccount, AdminAccountRepository, Long, LoginAccountData> {
    public AdminLoginService(
            KcAuthenticationProviderManager authenticationProviderManager,
            KcLoginManager<AdminAccount, AdminAccountRepository, Long> accountLoginManager
    ) {
        super(authenticationProviderManager, accountLoginManager);
    }

    @Override
    public Class<AdminAccount> getAccountEntityClass() {
        return AdminAccount.class;
    }
}
