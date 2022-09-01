package com.sycho.security.controller;

import com.keencho.lib.spring.security.resolver.annotation.KcsAccount;
import com.sycho.security.model.AdminAccount;
import com.sycho.security.model.UserAccount;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
