package com.keencho.spring.jpa.service;

import com.keencho.spring.jpa.model.SoccerPlayer;
import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionChildService {

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequired(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.save(soccerPlayer);
        throw new RuntimeException("throw exception");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void insertSupports(SoccerPlayer soccerPlayer){
        if (soccerPlayer.getId() > 5L) {
            throw new RuntimeException("throw exception");
        }
        soccerPlayerRepository.save(soccerPlayer);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void insertMandatory(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.save(soccerPlayer);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void insertNever(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.save(soccerPlayer);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertNotSupported(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.save(soccerPlayer);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertNested(SoccerPlayer soccerPlayer) {
        try {
            soccerPlayerRepository.save(soccerPlayer);
            throw new RuntimeException("throw error");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(SoccerPlayer soccerPlayer) {
        soccerPlayerRepository.save(soccerPlayer);
    }
}
