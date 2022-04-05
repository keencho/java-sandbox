package com.keencho.spring.jpa.service;

import com.keencho.spring.jpa.model.SoccerPlayer;
import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    @Autowired
    TransactionChildService transactionChildService;

    @Transactional
    public void required() {
        SoccerPlayer player1 = new SoccerPlayer();
        player1.setName("손흥민");

        try {
            transactionChildService.insertRequired(player1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SoccerPlayer player2 = new SoccerPlayer();
        player2.setName("박지성");
        soccerPlayerRepository.save(player2);
    }

    @Transactional
    public void supports_1() {
        doSupportsTest();
    }

    public void supports_2() {
        doSupportsTest();
    }

    private void doSupportsTest() {
        for (int i = 0; i < 10; i ++) {
            try {
                long seq = (long) i + 1;
                var soccerPlayer = new SoccerPlayer();
                soccerPlayer.setId(seq);
                soccerPlayer.setName("선수" + seq);

                transactionChildService.insertSupports(soccerPlayer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void mandatory() {
        SoccerPlayer player1 = new SoccerPlayer();
        player1.setName("손흥민");
        transactionChildService.insertMandatory(player1);
    }

    @Transactional
    public void never() {
        SoccerPlayer player1 = new SoccerPlayer();
        player1.setName("손흥민");
        transactionChildService.insertNever(player1);
    }

    @Transactional
    public void notSupported() {
        SoccerPlayer player1 = new SoccerPlayer();
        player1.setName("손흥민");
        transactionChildService.insertNotSupported(player1);
    }

    @Transactional
    public void nested() {
        SoccerPlayer player1 = new SoccerPlayer();
        player1.setName("손흥민");
        transactionChildService.insertNested(player1);

        SoccerPlayer player2 = new SoccerPlayer();
        player2.setName("박지성");
        soccerPlayerRepository.save(player2);
    }

    @Transactional
    public void requiresNew() {
        SoccerPlayer player1 = new SoccerPlayer();
        player1.setName("손흥민");
        transactionChildService.insertRequiresNew(player1);

        SoccerPlayer player2 = new SoccerPlayer();
        player2.setName("박지성");
        soccerPlayerRepository.save(player2);
        throw new RuntimeException("throw error on parent");
    }

}