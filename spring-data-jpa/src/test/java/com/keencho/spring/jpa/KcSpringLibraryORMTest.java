package com.keencho.spring.jpa;

import com.keencho.spring.jpa.dto.SoccerPlayerDTO;
import com.keencho.spring.jpa.model.Q;
import com.keencho.spring.jpa.model.QSoccerPlayer;
import com.keencho.spring.jpa.model.SoccerPlayer;
import com.keencho.spring.jpa.model.SoccerTeam;
import com.keencho.spring.jpa.repository.SoccerPlayerRepository;
import com.keencho.spring.jpa.repository.SoccerTeamRepository;
import com.keencho.spring.jpa.utils.FakerUtils;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Random;

/**
 * QueryDSL 테스트
 */
@SpringBootTest
public class QueryDSLTest {

    @Autowired
    SoccerTeamRepository soccerTeamRepository;

    @Autowired
    SoccerPlayerRepository soccerPlayerRepository;

    @PersistenceUnit
    EntityManagerFactory emf;

    Random rand = new Random();

    QSoccerPlayer q = Q.soccerPlayer;

    @BeforeEach
    public void initData() {
        var soccerTeamList = new ArrayList<SoccerTeam>();

        for (var i = 0; i < 5; i ++) {
            var st = new SoccerTeam();
            st.setName(FakerUtils.teamName());
            soccerTeamRepository.save(st);

            soccerTeamList.add(st);
        }

        for (var i = 0; i < 100; i ++) {
            var sp = new SoccerPlayer();
            sp.setName(FakerUtils.name());
            sp.setSoccerTeam(soccerTeamList.get(rand.nextInt(soccerTeamList.size())));
            sp.setHeight(i);
            sp.setWeight(200 - i);

            soccerPlayerRepository.save(sp);
        }
    }

    @Test
    @Transactional
    public void 조회_projection() {
        var bb = new BooleanBuilder();

        bb.and(q.name.containsIgnoreCase("h"));

        var res = soccerPlayerRepository.selectList(bb, SoccerPlayerDTO.bindings, SoccerPlayerDTO.queryHandler);

        Assert.isTrue(!res.isEmpty(), "result must not empty!");
    }

    @Test
    @Transactional
    public void 조회_페이징() {
        var list = soccerPlayerRepository.selectPage(null, SoccerPlayerDTO.bindings, new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 10;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return new QSort(q.id.desc());
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        }, SoccerPlayerDTO.queryHandler);

        Assert.isTrue(list.getContent().size() == 10, "size of content must be 10");
    }
}
