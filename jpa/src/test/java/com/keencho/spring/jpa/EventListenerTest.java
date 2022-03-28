package com.keencho.spring.jpa;

import com.keencho.spring.jpa.model.SoccerPlayer;
import com.keencho.spring.jpa.model.SoccerTeam;
import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import com.keencho.spring.jpa.repository.SoccerTeamRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventListenerTest {

    @Autowired
    SoccerTeamRepository soccerTeamRepository;

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    private final static String soccerTeamName = "토트넘";

    @BeforeEach
    public void beforeEach() {
        var soccerTeam = new SoccerTeam();
        soccerTeam.setName(soccerTeamName);

        soccerTeamRepository.save(soccerTeam);
    }

    @Test
    public void test() {
        var soccerTeam = soccerTeamRepository
                .findByName(soccerTeamName)
                .orElseThrow(() -> new RuntimeException("soccer team should not be null"));

        var soccerPlayer = new SoccerPlayer();
        soccerPlayer.setName("손흥민");
        soccerPlayer.setSoccerTeam(soccerTeam);

        soccerPlayerRepository.save(soccerPlayer);
    }
}
