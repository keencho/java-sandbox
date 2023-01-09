package com.keencho.spring.jpa.listeners;

import com.keencho.spring.jpa.model.SoccerPlayer;
import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

@Component
public class SoccerPlayerListeners implements SimpleEventListeners {

    private static SoccerPlayerRepository soccerPlayerRepository;

    @PrePersist
    public void prePersist(SoccerPlayer soccerPlayer) {
        System.out.println("size: " + soccerPlayerRepository.findAll().size());
    }
}
