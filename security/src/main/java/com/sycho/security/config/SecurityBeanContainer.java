package com.sycho.security.config;

import com.keencho.lib.spring.security.manager.KcAccountLoginManager;
import com.keencho.lib.spring.security.manager.KcAccountLoginManagerImpl;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManagerImpl;
import com.keencho.lib.spring.security.provider.KcUserDetailsAuthenticationProvider;
import com.keencho.lib.spring.security.service.KcLoginService;
import com.keencho.lib.spring.security.service.KcLoginServiceImpl;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.service.AdminAccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityBeanContainer {

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Autowired
    AdminAccountUserDetailsService adminAccountUserDetailsService;

    private final static Class<?> adminClazz = AdminAccount.class;
    private final static Class<?> userClazz = UserAccount.class;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KcAuthenticationProviderManager authenticationProviderManager() {
        var authenticationProviderManager = new KcAuthenticationProviderManagerImpl();

        authenticationProviderManager.addAuthenticationProvider(
                adminClazz,
                new KcUserDetailsAuthenticationProvider(this.bCryptPasswordEncoder(), this.adminAccountUserDetailsService)
        );

        return authenticationProviderManager;
    }

    @Bean
    public KcAccountLoginManager<AdminAccount, AdminAccountRepository, Long> adminAccountLoginManager() {
        return new KcAccountLoginManagerImpl<>(adminAccountRepository);
    }

    @Bean
    public KcLoginService<AdminAccount, AdminAccountRepository, Long, LoginAccountData> adminAccountLoginService() {
        return new KcLoginServiceImpl<>(this.authenticationProviderManager(), this.adminAccountLoginManager()) {
            @Override
            protected Class<?> getAccountEntityClass() {
                return adminClazz;
            }
        };
    }
}
