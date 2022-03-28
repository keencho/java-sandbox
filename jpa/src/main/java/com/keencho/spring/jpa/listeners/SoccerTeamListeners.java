package com.keencho.spring.jpa.listeners;

import com.keencho.spring.jpa.model.SoccerTeam;
import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import com.keencho.spring.jpa.repository.SoccerTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;

@Component
public class SoccerTeamListeners{

    @Autowired
    SoccerTeamRepository soccerTeamRepository;

    @PrePersist
    public void prePersist(SoccerTeam soccerTeam) {
        System.out.println("size: " + soccerTeamRepository.findAll().size());
    }
}
