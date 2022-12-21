package com.keencho.spring.jpa;

import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import com.keencho.spring.jpa.service.TransactionPropagationTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * 트랜잭션 전파레벨 테스트
 */
@SpringBootTest
public class TransactionPropagationTest {

    @Autowired
    TransactionPropagationTestService transactionPropagationTestService;

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    @Test
    public void REQUIRED() {
        transactionPropagationTestService.required();

        var list = soccerPlayerRepository.findAll();

        Assert.notNull(list, "soccer player list must not be null");
    }

    @Test
    public void SUPPORTS_1() {
        transactionPropagationTestService.supports_1();

        var list = soccerPlayerRepository.findAll();

        Assert.isTrue(list.size() == 10, "size of soccer player list must be 2");
    }

    @Test
    public void SUPPORTS_2() {
        transactionPropagationTestService.supports_2();

        var list = soccerPlayerRepository.findAll();

        Assert.isTrue(list.size() == 10, "size of soccer player list must be 2");
    }

    @Test
    public void MANDATORY() {
        transactionPropagationTestService.mandatory();

        soccerPlayerRepository.findByName("손흥민").orElseThrow(() -> new RuntimeException("soccer player must not be null"));
    }

    @Test
    public void NEVER() {
        transactionPropagationTestService.never();

        soccerPlayerRepository.findByName("손흥민").orElseThrow(() -> new RuntimeException("soccer player must not be null"));
    }

    @Test
    public void NOT_SUPPORTED() {
        transactionPropagationTestService.notSupported();

        soccerPlayerRepository.findByName("손흥민").orElseThrow(() -> new RuntimeException("soccer player must not be null"));
    }

    @Test
    public void NESTED() {
        transactionPropagationTestService.nested();

        var soccerPlayerList = soccerPlayerRepository.findAll();

        Assert.isTrue(soccerPlayerList.size() == 1, "size of soccer player list must be 1");
    }

    @Test
    public void REQUIRES_NEW() {
        try {
            transactionPropagationTestService.requiresNew();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        var soccerPlayerList = soccerPlayerRepository.findAll();

        Assert.isTrue(soccerPlayerList.size() == 1, "size of soccer player list must be 1");
    }
}
