package com.sycho.security.controller;

import com.sycho.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UserAccountRepository repository;

    @PostMapping("/login")
    public void login(
            @RequestBody Map<String, String> map
    ) {
        var id = map.get("id");
        var pw = map.get("pw");

        if (!(id.equals("1") && pw.equals("1"))) {
            throw new RuntimeException("Not Matched");
        }
        System.out.println(map);
    }
}
