package com.sycho.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    @GetMapping("/login")
    public String login(
            @AuthenticationPrincipal Object obj
    ) {
        return "login";
    }
}
