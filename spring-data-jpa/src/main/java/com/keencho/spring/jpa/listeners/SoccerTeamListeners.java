package com.keencho.spring.jpa.listeners;

import com.keencho.spring.jpa.model.SoccerTeam;
import com.keencho.spring.jpa.repository.SoccerTeamRepository;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

@Component
public class SoccerTeamListeners implements SimpleEventListeners {

    private static SoccerTeamRepository soccerTeamRepository;

    @PrePersist
    public void prePersist(SoccerTeam soccerTeam) {
        System.out.println("size: " + soccerTeamRepository.findAll().size());
    }
}
