package com.sycho.security.config;

import com.keencho.lib.spring.security.resolver.KcWebAccountResolver;
import com.keencho.lib.spring.security.resolver.manager.KcAccountResolverManager;
import com.sycho.security.model.AccountCustomObjectTestModel;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    KcAccountResolverManager accountResolverManager;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new KcWebAccountResolver(accountResolverManager));
    }
}
