package com.keencho.spring.jpa.event;

import com.keencho.spring.jpa.event.model.SoccerTeam;
import com.keencho.spring.jpa.event.repository.SoccerTeamRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class SoccerTeamEventListener {

    private SoccerTeamRepository soccerTeamRepository;

    @Lazy
    public SoccerTeamEventListener(SoccerTeamRepository soccerTeamRepository) {
        this.soccerTeamRepository = soccerTeamRepository;
    }

    public SoccerTeamEventListener() { }

    @PostLoad
    private void test(SoccerTeam soccerTeam) {
        System.out.println("음..");
    }

    @PrePersist
    private void prePersist(SoccerTeam soccerTeam) {
        System.out.println("PrePersist\n" + soccerTeam.toString());
    }

    @PostPersist
    private void postPersist(SoccerTeam soccerTeam) {
        System.out.println("PostPersist\n" + soccerTeam.toString());
    }

    @PreUpdate
    private void preUpdate(SoccerTeam soccerTeam) {
        System.out.println("PreUpdate\n" + soccerTeam.toString());
    }

    @PostUpdate
    private void postUpdate(SoccerTeam soccerTeam) {
        System.out.println("PostUpdate\n" + soccerTeam.toString());
    }

    @PreRemove
    private void preRemove(SoccerTeam soccerTeam) {
        System.out.println("PreRemove\n" + soccerTeam.toString());
    }

    @PostRemove
    private void postRemove(SoccerTeam soccerTeam) {
        System.out.println("PostRemove\n" + soccerTeam.toString());
    }

//    @PostLoad
//    private void postLoad(SoccerTeam soccerTeam) {
//        System.out.println("PostLoad - " + soccerTeam.getTeamName());
//    }
}
