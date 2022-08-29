package com.sycho.security.service;

import com.keencho.lib.spring.security.manager.KcAccountLoginManager;
import com.keencho.lib.spring.security.model.KcSecurityAccount;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.model.LoginAccountType;
import com.sycho.security.repository.AdminAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class AdminAccountUserDetailsService implements UserDetailsService {

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("Username must be provided!");
        }

        var account = adminAccountRepository.findByLoginId(username);

        if (account == null) {
            throw new UsernameNotFoundException("Username not found, username=" + username);
        }

        var authorities = account.authorities();

        var loginData = new LoginAccountData();
        loginData.setLoginId(account.getLoginId());
        loginData.setLoginAccountType(LoginAccountType.ADMIN);
        loginData.setAuthorities(authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));

        return new KcSecurityAccount<>(
                account.getLoginId(), account.getPassword(), new HashSet<>(authorities),
                account.isAccountNonExpired(), account.isAccountNonLocked(), account.isCredentialsNonExpired(), account.isEnabled(),
                loginData
        );
    }
}
