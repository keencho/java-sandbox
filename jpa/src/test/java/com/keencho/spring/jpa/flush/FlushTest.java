package com.keencho.spring.jpa.flush;

import com.keencho.spring.jpa.flush.model.SoccerPlayer;
import com.keencho.spring.jpa.flush.model.SoccerTeam;
import com.keencho.spring.jpa.flush.repository.SoccerTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@SpringBootTest
@Transactional
public class FlushTest {

    @Autowired
    SoccerTeamRepository soccerTeamRepository;

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    void flushAUTO() {
        EntityManager em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.AUTO);
        em.getTransaction().begin();

        var soccerPlayer = new SoccerPlayer();
        soccerPlayer.setName("손흥민");
        em.persist(soccerPlayer);

        em.createQuery("SELECT f FROM SoccerTeam f").getResultList();

        Query q = em.createQuery("SELECT p FROM SoccerPlayer p WHERE p.name = :name");
        q.setParameter("name", "손흥민");

        q.getResultList();

        em.getTransaction().commit();
        em.close();
    }

    @Test
    void flushCOMMIT() {

    }
}
