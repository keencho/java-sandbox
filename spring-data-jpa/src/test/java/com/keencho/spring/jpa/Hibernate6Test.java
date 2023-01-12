package com.keencho.spring.jpa;

import com.keencho.spring.jpa.model.SoccerPlayer;
import com.keencho.spring.jpa.model.SoccerTeam;
import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import com.keencho.spring.jpa.repository.SoccerTeamRepository;
import com.keencho.spring.jpa.utils.FakerUtils;
import io.jsonwebtoken.lang.Assert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class Hibernate6Test {

    @Autowired
    SoccerTeamRepository soccerTeamRepository;

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    @PersistenceContext
    EntityManager entityManager;

    private final static String[] SOCCER_TEAM_NAMES = new String[] {"리버풀", "맨유", "맨시티", "토트넘"};
    private final static int SOCCER_PLAYER_TOTAL_COUNT = 100;
    private final static List<String> NAME_LIST = new ArrayList<>();
    private final static String QUERY_BLOCK = """
            SELECT p.name as name
            FROM SoccerPlayer p
            %s
            SELECT t.name as name
            FROM SoccerTeam t
            """;

    @BeforeAll
    public void beforeAll() {
        var soccerTeamList = Arrays.stream(SOCCER_TEAM_NAMES).map(name ->soccerTeamRepository.save(new SoccerTeam(name))).toList();

        var rand = new Random();

        IntStream.range(0, SOCCER_PLAYER_TOTAL_COUNT).forEach(idx -> soccerPlayerRepository.save(new SoccerPlayer(FakerUtils.name(), soccerTeamList.get(rand.nextInt(soccerTeamList.size())))));

        Arrays.stream(SOCCER_TEAM_NAMES).forEach(name -> soccerPlayerRepository.save(new SoccerPlayer(name, soccerTeamList.get(rand.nextInt(soccerTeamList.size())))));

        var st =  soccerTeamRepository.findAll();
        var sp = soccerPlayerRepository.findAll();

        st.forEach(item -> NAME_LIST.add(item.getName()));
        sp.forEach(item -> NAME_LIST.add(item.getName()));
    }

    @Test
    void dataTest() {
        Assert.isTrue(soccerTeamRepository.findAll().size() == SOCCER_TEAM_NAMES.length);
        Assert.isTrue(soccerPlayerRepository.findAll().size() == SOCCER_PLAYER_TOTAL_COUNT + SOCCER_TEAM_NAMES.length);
    }

    @Test
    void UNION() {
        List<?> list = entityManager.createQuery(String.format(QUERY_BLOCK, "UNION")).getResultList();

        Assert.isTrue(NAME_LIST.stream().distinct().toList().size() == list.size());
    }

    @Test
    void INTERSECT() {
        List<?> list = entityManager.createQuery(String.format(QUERY_BLOCK, "INTERSECT")).getResultList();

        Assert.isTrue(list.size() == SOCCER_TEAM_NAMES.length);
    }

    @Test
    void EXCEPT() {
        List<?> list = entityManager.createQuery(String.format(QUERY_BLOCK, "EXCEPT")).getResultList();

        Assert.isTrue(list.size() == SOCCER_PLAYER_TOTAL_COUNT);
    }
}
