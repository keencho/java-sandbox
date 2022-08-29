package com.sycho.security.service;

import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataInitService {

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        for (var i = 1; i < 10; i ++) {
            var adminAccount = new AdminAccount();
            var id = String.valueOf(i);
            var pw = passwordEncoder.encode(String.valueOf(i));
            adminAccount.setLoginId(id);
            adminAccount.setPassword(pw);

            var userAccount = new UserAccount();
            userAccount.setLoginId(id);
            userAccount.setPassword(pw);

            adminAccountRepository.save(adminAccount);
            userAccountRepository.save(userAccount);
        }
    }
}
