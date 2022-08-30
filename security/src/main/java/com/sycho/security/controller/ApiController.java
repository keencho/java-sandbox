package com.sycho.security.controller;

import com.keencho.lib.spring.security.model.KcAccountBaseModel;
import com.keencho.lib.spring.security.repository.KcAccountRepository;
import com.keencho.lib.spring.security.service.KcLoginService;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.LoginAccountData;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.repository.UserAccountRepository;
import com.sycho.security.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    List<KcLoginService<? extends KcAccountBaseModel, ? extends KcAccountRepository, ?, LoginAccountData>> kcLoginService;

    Map<Class<? extends KcAccountBaseModel>, KcLoginService<? extends KcAccountBaseModel, ? extends KcAccountRepository, ?, LoginAccountData>> loginServiceMap;

    @PostConstruct
    public void initMap() {
        loginServiceMap = new HashMap<>();

        for (var svc : kcLoginService) {
            loginServiceMap.put(svc.getAccountEntityClass(), svc);
        }
    }

    @Autowired
    AdminAccountRepository adminAccountRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @PostMapping("/login")
    public LoginAccountData login(
            @RequestBody Map<String, String> map,
            @RequestParam String type
    ) {
        var id = map.get("id");
        var pw = map.get("pw");

        var clazz = "admin".equals(type) ? AdminAccount.class : UserAccount.class;

        return this.loginServiceMap.get(clazz).login(id, pw);
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
