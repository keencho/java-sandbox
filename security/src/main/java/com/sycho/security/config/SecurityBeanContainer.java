package com.sycho.security.config;

import com.keencho.lib.spring.common.config.KcGlobalExceptionHandler;
import com.keencho.lib.spring.security.manager.KcAccountLoginManager;
import com.keencho.lib.spring.security.manager.KcDefaultAccountLoginManager;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManagerImpl;
import com.keencho.lib.spring.security.provider.KcUserDetailsAuthenticationProvider;
import com.keencho.lib.spring.security.service.KcDefaultLoginService;
import com.keencho.lib.spring.security.service.KcLoginService;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.service.AdminAccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnMissingBean(annotation = ControllerAdvice.class)
public class SecurityBeanContainer {

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Autowired
    AdminAccountUserDetailsService adminAccountUserDetailsService;

    private final static Class<AdminAccount> adminClass = AdminAccount.class;
    private final static Class<UserAccount> userClazz = UserAccount.class;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KcGlobalExceptionHandler globalExceptionHandler() {
        return new KcGlobalExceptionHandler() {};
    }

    @Bean
    public KcAuthenticationProviderManager authenticationProviderManager() {
        var authenticationProviderManager = new KcAuthenticationProviderManagerImpl();

        authenticationProviderManager.addAuthenticationProvider(
                adminClass,
                new KcUserDetailsAuthenticationProvider(this.bCryptPasswordEncoder(), this.adminAccountUserDetailsService)
        );


        return authenticationProviderManager;
    }

    @Bean
    public KcLoginService<AdminAccount, AdminAccountRepository, Long, LoginAccountData> adminAccountLoginService() {
        return new KcDefaultLoginService<>(this.authenticationProviderManager(), this.adminAccountLoginManager()) {
            @Override
            public Class<AdminAccount> getAccountEntityClass() {
                return adminClass;
            }
        };
    }

    @Bean
    public KcAccountLoginManager<AdminAccount, AdminAccountRepository, Long> adminAccountLoginManager() {
        return new KcDefaultAccountLoginManager<>(adminAccountRepository) {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                var set = new HashSet<String>();
                set.add(AccountRoleCode.ROLE_COMMON);
                set.add(AccountRoleCode.ROLE_ADMIN);

                return set.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            }

            @Override
            public int getMaxLoginAttemptCount() {
                return 10;
            }
        };
    }
}
