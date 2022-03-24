package com.keencho.spring.jpa.event;

import com.keencho.spring.jpa.event.model.SoccerTeam;
import com.keencho.spring.jpa.event.repository.SoccerPlayerRepository;
import com.keencho.spring.jpa.event.repository.SoccerTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
class JpaEventListenerApplicationTests {

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    @Autowired
    SoccerTeamRepository soccerTeamRepository;

    @BeforeEach
    void initData() {
        var liverPool = SoccerTeam.builder()
                .teamName("리버풀")
                .homeTown("안필드")
                .managerName("클롭")
                .foundingDate(LocalDate.of(2000, 1, 1))
                .lastMatchDate(LocalDate.of(2022, 3, 1))
                .build();

        var manchesterUnited = SoccerTeam.builder()
                .teamName("맨체스터 유나이티드")
                .homeTown("올드 트래퍼드")
                .managerName("랑닉")
                .foundingDate(LocalDate.of(2002, 10, 1))
                .lastMatchDate(LocalDate.of(2021, 3, 1))
                .build();

        soccerTeamRepository.save(liverPool);
        soccerTeamRepository.save(manchesterUnited);
    }

    @Test
    @Rollback(value = false)
    void test() {
        soccerTeamRepository
                .findByTeamName("리버풀")
                .ifPresent(soccerTeam -> soccerTeam.setLastMatchDate(LocalDate.of(2018, 3, 1)));

        soccerTeamRepository
                .findByTeamName("맨체스터 유나이티드")
                .ifPresent(soccerTeam -> soccerTeamRepository.delete(soccerTeam));
    }

}
