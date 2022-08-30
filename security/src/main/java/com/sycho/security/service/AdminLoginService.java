//package com.sycho.security.service;
//
//import com.keencho.lib.spring.security.manager.KcAccountLoginManager;
//import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
//import com.keencho.lib.spring.security.service.KcDefaultLoginService;
//import com.sycho.security.model.AdminAccount;
//import com.sycho.security.model.LoginAccountData;
//import com.sycho.security.repository.AdminAccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdminLoginService extends KcDefaultLoginService<AdminAccount, AdminAccountRepository, Long, LoginAccountData> {
//
//    @Autowired
//    KcAuthenticationProviderManager authenticationProviderManager;
//
//    @Autowired
//    KcAccountLoginManager<AdminAccount, AdminAccountRepository, Long> adminAccountLoginManager;
//
//    public AdminLoginService(
//            KcAuthenticationProviderManager authenticationProviderManager,
//            KcAccountLoginManager<AdminAccount, AdminAccountRepository, Long> accountLoginManager
//    ) {
//        super(authenticationProviderManager, accountLoginManager);
//    }
//
//    @Override
//    protected Class<AdminAccount> getAccountEntityClass() {
//        return AdminAccount.class;
//    }
//}
