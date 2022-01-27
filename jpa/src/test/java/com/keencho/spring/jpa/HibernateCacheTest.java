package com.keencho.spring.jpa;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.keencho.spring.jpa.cache.repository.CacheTestRepository;
import com.keencho.spring.jpa.cache.service.CacheTestService;

@SpringBootTest
class HibernateCacheTest {

    @Autowired
    CacheTestRepository cacheTestRepository;

    @Autowired
    CacheTestService cacheTestService;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    @Transactional
    void firstLevelCacheTest() {

        sessionFactory.getStatistics().setStatisticsEnabled(true);

        cacheTestService.insertDummyData();

        cacheTestRepository.findAll();

        var a = cacheTestRepository.findById(1L);
        var b = cacheTestRepository.findById(2L);

        Assert.notNull(a, "no way");
        Assert.notNull(b, "no way");
    }
}
