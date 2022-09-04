package com.sycho.security.controller;

import com.keencho.lib.spring.security.model.KcAccountBaseModel;
import com.keencho.lib.spring.security.model.KcSecurityAccount;
import com.keencho.lib.spring.security.repository.KcAccountRepository;
import com.keencho.lib.spring.security.resolver.annotation.KcsAccount;
import com.keencho.lib.spring.security.resolver.annotation.KcsAccountType;
import com.keencho.lib.spring.security.service.KcLoginService;
import com.sycho.security.model.AccountCustomObjectTestModel;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.UserAccount;
import com.sycho.security.repository.AdminAccountRepository;
import com.sycho.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    List<KcLoginService<? extends KcAccountBaseModel, ? extends KcAccountRepository<?, ?>>> kcLoginService;

    Map<Class<? extends KcAccountBaseModel>, KcLoginService<? extends KcAccountBaseModel, ? extends KcAccountRepository<?, ?>>> loginServiceMap;

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
    public Object login(
            @RequestBody Map<String, String> map,
            @RequestParam String type,
            HttpServletResponse response
    ) {
        var id = map.get("id");
        var pw = map.get("pw");

        var clazz = "admin".equals(type) ? AdminAccount.class : UserAccount.class;

        return this.loginServiceMap.get(clazz).login(response, id, pw);
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

    @GetMapping("/auth/test/admin")
    public AdminAccount authTestAdmin(
            @KcsAccount(required = true) AdminAccount adminAccount,
            @KcsAccount(accountType = KcsAccountType.SECURITY_ACCOUNT) KcSecurityAccount securityAccount,
            @KcsAccount(accountType = KcsAccountType.SECURITY_ACCOUNT_CUSTOM_OBJECT) AccountCustomObjectTestModel testModel
            ) {
        return adminAccount;
    }

    @GetMapping("/auth/test/user")
    public UserAccount authTestUser(
            @KcsAccount(required = true) UserAccount userAccount
    ) {
        return userAccount;
    }
}
