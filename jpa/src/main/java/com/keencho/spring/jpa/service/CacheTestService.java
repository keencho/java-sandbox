package com.keencho.spring.jpa.service;

import com.keencho.spring.jpa.model.CacheTestModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.keencho.spring.jpa.repo.CacheTestRepository;

import java.util.Arrays;
import java.util.Random;

@Service
@Transactional
public class CacheTestService {

    @Autowired
    CacheTestRepository cacheTestRepository;

    @Autowired
    SessionFactory sessionFactory;

    public CacheTestModel secondLevelTest(Long id) {
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("=========================");
        System.out.println("find entity start");
        var e = cacheTestRepository.findById(id).orElse(null);
        System.out.println("second level cache hit count: " + sessionFactory.getStatistics().getSecondLevelCacheHitCount());
        System.out.println("second level cache miss count: " + sessionFactory.getStatistics().getSecondLevelCacheMissCount());
        System.out.println("second level cache put count: " + sessionFactory.getStatistics().getSecondLevelCachePutCount());
        System.out.println("find entity end");

        return e;
    }

    public void insertDummyData() {
        var r = Arrays.asList("banana", "monkey", "apple", "bear");

//        System.out.println(sessionFactory.getSessionFactoryOptions().getUuid());

        Random rand = new Random();
        for (var a = 0; a < 10; a ++) {
            CacheTestModel cacheTestModel = new CacheTestModel();
            cacheTestModel.setKeyword(r.get(rand.nextInt(r.size())));

            cacheTestRepository.save(cacheTestModel);
        }
    }
}
