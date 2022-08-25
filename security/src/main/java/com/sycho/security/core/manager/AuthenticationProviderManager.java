package com.sycho.security.core.manager;

import com.sycho.security.core.AccountResolver;
import org.springframework.security.authentication.AuthenticationProvider;

import java.lang.reflect.Type;
import java.util.Collection;

public interface AuthenticationProviderManager {

    Collection<AuthenticationProvider> getProviders();

    AuthenticationProvider getAuthenticationProvider(Type accountClass) throws ClassNotFoundException;

    AccountResolver<?, ?> getAccountAndUserResolver(Type accountClass) throws ClassNotFoundException;

    boolean accountResolverExists(Type accountClass);

}
