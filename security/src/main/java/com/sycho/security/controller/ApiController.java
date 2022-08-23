package com.sycho.security.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/login")
    public void login(
            @RequestBody Map<String, String> map
    ) {
        System.out.println(map);
    }

    @GetMapping("/login")
    public void login2() {
        System.out.println("dfdf");
    }
}
