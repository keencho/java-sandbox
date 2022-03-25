package com.keencho.spring.jpa.flush;

import com.keencho.spring.jpa.flush.model.SoccerPlayer;
import com.keencho.spring.jpa.flush.model.SoccerTeam;
import com.keencho.spring.jpa.flush.repository.SoccerTeamRepository;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
        EntityManager em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);
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
    void flushALWAYS() {
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        session.setHibernateFlushMode(FlushMode.ALWAYS);
        em.getTransaction().begin();

        var soccerPlayer = new SoccerPlayer();
        soccerPlayer.setName("손흥민");
        em.persist(soccerPlayer);

        Query q = em.createQuery("SELECT f FROM SoccerTeam f");
        q.getResultList();

        var insertedPlayer = em.createQuery("SELECT p FROM SoccerPlayer p").getResultList().stream().findFirst().orElse(null);

        Assert.notNull(insertedPlayer, "One soccer player should be inquired.");

        em.getTransaction().commit();
        em.close();
    }

    @Test
    void flushMANUAL() {
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        session.setHibernateFlushMode(FlushMode.MANUAL);
        em.getTransaction().begin();

        var soccerPlayer = new SoccerPlayer();
        soccerPlayer.setName("손흥민");
        session.save(soccerPlayer);

        session.flush();

        var insertedPlayer = em.createQuery("SELECT p FROM SoccerPlayer p").getResultList().stream().findFirst().orElse(null);

        Assert.notNull(insertedPlayer, "One soccer player should be inquired.");

        em.getTransaction().commit();
        em.close();
    }
}
