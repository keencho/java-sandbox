package com.keencho.spring.jpa;

import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import com.keencho.spring.jpa.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TransactionPropagationTest {

    @Autowired
    TransactionService transactionService;

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    @Test
    public void REQUIRED() {
        transactionService.required();

        var list = soccerPlayerRepository.findAll();

        Assert.notNull(list, "soccer player list must not be null");
    }

    @Test
    public void SUPPORTS_1() {
        transactionService.supports_1();

        var list = soccerPlayerRepository.findAll();

        Assert.isTrue(list.size() == 10, "size of soccer player list must be 2");
    }

    @Test
    public void SUPPORTS_2() {
        transactionService.supports_2();

        var list = soccerPlayerRepository.findAll();

        Assert.isTrue(list.size() == 10, "size of soccer player list must be 2");
    }

    @Test
    public void MANDATORY() {
        transactionService.mandatory();

        soccerPlayerRepository.findByName("손흥민").orElseThrow(() -> new RuntimeException("soccer player must not be null"));
    }

    @Test
    public void NEVER() {
        transactionService.never();

        soccerPlayerRepository.findByName("손흥민").orElseThrow(() -> new RuntimeException("soccer player must not be null"));
    }

    @Test
    public void NOT_SUPPORTED() {
        transactionService.notSupported();

        soccerPlayerRepository.findByName("손흥민").orElseThrow(() -> new RuntimeException("soccer player must not be null"));
    }

    @Test
    public void NESTED() {
        transactionService.nested();

        var soccerPlayerList = soccerPlayerRepository.findAll();

        Assert.isTrue(soccerPlayerList.size() == 1, "size of soccer player list must be 1");
    }

    @Test
    public void REQUIRES_NEW() {
        try {
            transactionService.requiresNew();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        var soccerPlayerList = soccerPlayerRepository.findAll();

        Assert.isTrue(soccerPlayerList.size() == 1, "size of soccer player list must be 1");
    }
}
