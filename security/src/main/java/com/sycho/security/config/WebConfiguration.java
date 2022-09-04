package com.sycho.security.config;

import com.keencho.lib.spring.security.model.KcAccountBaseModel;
import com.keencho.lib.spring.security.model.KcSecurityAccount;
import com.keencho.lib.spring.security.resolver.KcWebSecurityAccountCustomObjectParser;
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
        resolvers.add(new KcWebAccountResolver(accountResolverManager, this.customObjectParser()));
    }

    private KcWebSecurityAccountCustomObjectParser<AccountCustomObjectTestModel> customObjectParser() {
        return securityAccount -> {
            var resolver = accountResolverManager.getKcAccountResolver(securityAccount.getAccountEntityClass());

            if (resolver != null) {
                var account = resolver.getAccountBySecurityAccount(securityAccount);

                var model = new AccountCustomObjectTestModel();
                if (account instanceof AdminAccount adminAccount) {
                    model.setId(adminAccount.getId());
                    model.setLoginId(adminAccount.getLoginId());
                    model.setKey("ADMIN");
                }

                if (account instanceof UserAccount userAccount) {
                    model.setId(userAccount.getId());
                    model.setLoginId(userAccount.getLoginId());
                    model.setKey("USER");
                }

                return model;
            }

            return null;
        };
    }
}
