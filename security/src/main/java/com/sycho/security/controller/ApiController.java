package com.sycho.security.controller;

import com.sycho.security.model.LoginAccountData;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.repository.UserAccountRepository;
import com.sycho.security.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    AdminLoginService adminLoginService;

    @PostMapping("/login")
    public LoginAccountData login(
            @RequestBody Map<String, String> map
    ) {
        var id = map.get("id");
        var pw = map.get("pw");

        return adminLoginService.login(id, pw);
    }

    @GetMapping("/list/account")
    public Map<String, List<?>> listAccount() {
        var map = new HashMap<String, List<?>>();

        var admin = adminAccountRepository.findAll();
        var user = userAccountRepository.findAll();

        map.put("admin", admin);
        map.put("user", user);

        return map;
    }
}
