package com.keencho.spring.jpa.transaction.controller;

import com.keencho.spring.jpa.transaction.service.TransactionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transaction-test")
public class TransactionTestController {

    @Autowired
    TransactionTestService transactionTestService;

    @GetMapping
    public ResponseEntity<?> test(
            @RequestParam String payKey
    ) {
        return ResponseEntity.ok(transactionTestService.save(payKey, 10000));
    }
}
