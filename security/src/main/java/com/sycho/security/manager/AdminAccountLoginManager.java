//package com.sycho.security.manager;
//
//import com.keencho.lib.spring.security.manager.KcDefaultAccountLoginManager;
//import com.sycho.security.config.AccountRoleCode;
//import com.sycho.security.model.AdminAccount;
//import com.sycho.security.repository.AdminAccountRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.stream.Collectors;
//
//@Component
//public class AdminAccountLoginManager extends KcDefaultAccountLoginManager<AdminAccount, AdminAccountRepository, Long> {
//
//    public AdminAccountLoginManager(AdminAccountRepository adminAccountRepository) {
//        super(adminAccountRepository);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        var set = new HashSet<String>();
//        set.add(AccountRoleCode.ROLE_COMMON);
//        set.add(AccountRoleCode.ROLE_ADMIN);
//
//        return set.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public int getMaxLoginAttemptCount() {
//        return 10;
//    }
//}
