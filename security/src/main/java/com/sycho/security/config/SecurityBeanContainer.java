package com.sycho.security.config;

import com.keencho.lib.spring.common.config.KcGlobalExceptionHandler;
import com.keencho.lib.spring.security.manager.KcAccountLoginManager;
import com.keencho.lib.spring.security.manager.KcDefaultAccountLoginManager;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManagerImpl;
import com.keencho.lib.spring.security.provider.KcUserDetailsAuthenticationProvider;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.service.AdminAccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@ConditionalOnMissingBean(annotation = ControllerAdvice.class)
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
    public KcGlobalExceptionHandler globalExceptionHandler() {
        return new KcGlobalExceptionHandler() {};
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
        return new KcDefaultAccountLoginManager<>(adminAccountRepository);
    }

    /**
     * 아래와 같이 KcLoginService를 bean으로 등록하여 사용하거나 com.sycho.security.service.AdminLoginService와 같이 직접 구현해서 사용하는 방식 2가지가 있다.
     * 사실 '로그인' 이라는 비즈니스 로직을 공통으로 처리하는것도 웃기니 AdminLoginService와 같이 각각 정의해서 사용하는게 맞을지도?
     * @return
     */
//    @Bean
//    public KcLoginService<AdminAccount, AdminAccountRepository, Long, LoginAccountData> adminAccountLoginService() {
//        return new KcDefaultLoginService<>(this.authenticationProviderManager(), this.adminAccountLoginManager()) {
//            @Override
//            protected Class<?> getAccountEntityClass() {
//                return adminClazz;
//            }
//        };
//    }
}
