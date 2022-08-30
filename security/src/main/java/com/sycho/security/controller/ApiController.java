package com.sycho.security.controller;

import com.keencho.lib.spring.security.service.KcLoginService;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UserAccountRepository repository;

    @Autowired
    KcLoginService<AdminAccount, AdminAccountRepository, Long, LoginAccountData> adminLoginService;

    @PostMapping("/login")
    public LoginAccountData login(
            @RequestBody Map<String, String> map
    ) {
        var id = map.get("id");
        var pw = map.get("pw");

        return adminLoginService.login(id, pw);
    }
}
