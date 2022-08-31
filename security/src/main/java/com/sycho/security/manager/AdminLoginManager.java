package com.sycho.security.manager;

import com.keencho.lib.spring.security.manager.KcDefaultLoginManager;
import com.keencho.lib.spring.security.model.KcSecurityAccount;
import com.sycho.security.config.AccountRoleCode;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.model.LoginAccountType;
import com.sycho.security.repository.AdminAccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class AdminLoginManager extends KcDefaultLoginManager<AdminAccount, AdminAccountRepository, Long> {

    public AdminLoginManager(AdminAccountRepository adminAccountRepository) {
        super(adminAccountRepository);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var set = new HashSet<String>();
        set.add(AccountRoleCode.ROLE_COMMON);
        set.add(AccountRoleCode.ROLE_ADMIN);

        return set.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public int getMaxLoginAttemptCount() {
        return 5;
    }

    @Override
    public int getMaxLongTermNonUseAllowDay() {
        return 90;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("Username must be provided!");
        }

        var account = this.findByLoginId(username);

        if (account == null) {
            throw new UsernameNotFoundException("Username not found, username=" + username);
        }

        var authorities = this.getAuthorities();

        var loginData = new LoginAccountData();
        loginData.setLoginId(account.getLoginId());
        loginData.setLoginAccountType(LoginAccountType.ADMIN);
        loginData.setAuthorities(authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));

        return new KcSecurityAccount(
                account.getLoginId(), account.getPassword(), new HashSet<>(authorities),
                account.isAccountNonExpired(), account.isAccountNonLocked(), account.isCredentialsNonExpired(), account.isEnabled(),
                loginData
        );
    }
}
