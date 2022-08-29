package com.sycho.security.service;

import com.keencho.lib.spring.security.manager.KcAccountLoginManager;
import com.keencho.lib.spring.security.model.KcSecurityAccount;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.service.KcLoginService;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.repository.AdminAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService implements KcLoginService<AdminAccount, AdminAccountRepository, Long, LoginAccountData> {

    @Autowired
    KcAuthenticationProviderManager authenticationProviderManager;

    @Autowired
    KcAccountLoginManager<AdminAccount, AdminAccountRepository, Long> adminAcocuntLoginManager;

    @Override
    public LoginAccountData login(String loginId, String password) {
        Authentication authentication = null;
        var token = new UsernamePasswordAuthenticationToken(loginId, password);

        try {
            var authenticationProvider = authenticationProviderManager.getAuthenticationProvider(AdminAccount.class);
            authentication = authenticationProvider.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {

        } catch (LockedException ex) {

        } catch (DisabledException ex) {

        } catch (Exception ex) {

        }

        adminAcocuntLoginManager.updateDtLastAccessedAt(token.getPrincipal().toString());

        var securityUser = (KcSecurityAccount<LoginAccountData>) authentication.getPrincipal();

        return securityUser.getData();
    }

}
