package com.sycho.security.core.manager;

import com.sycho.security.core.AccountResolver;
import org.springframework.security.authentication.AuthenticationProvider;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationProviderManagerImpl implements AuthenticationProviderManager {

    private Map<Type, AuthenticationProvider> apMap = new HashMap<>();
    private Map<Type, AccountResolver<?, ?>> accountResolverMap = new HashMap<>();

    public AuthenticationProviderManagerImpl() {
    }

    @Override
    public Collection<AuthenticationProvider> getProviders() {
        return this.apMap.values();
    }

    @Override
    public AuthenticationProvider getAuthenticationProvider(Type accountClass) throws ClassNotFoundException {
        if (this.apMap.containsKey(accountClass)) {
            return apMap.get(accountClass);
        }

        throw new ClassNotFoundException();
    }

    @Override
    public AccountResolver<?, ?> getAccountAndUserResolver(Type accountClass) throws ClassNotFoundException {
        if (this.accountResolverMap.containsKey(accountClass)) {
            return accountResolverMap.get(accountClass);
        }

        throw new ClassNotFoundException();
    }

    @Override
    public boolean accountResolverExists(Type accountClass) {
        return this.accountResolverMap.containsKey(accountClass);
    }
}
