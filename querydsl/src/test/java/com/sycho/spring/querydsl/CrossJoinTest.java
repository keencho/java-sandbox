package com.sycho.spring.querydsl;

import com.mysema.commons.lang.Assert;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sycho.spring.querydsl.model.cross_join.*;
import com.sycho.spring.querydsl.repository.cross_join.CompanyRepository;
import com.sycho.spring.querydsl.repository.cross_join.DevTeamMembersRepository;
import com.sycho.spring.querydsl.repository.cross_join.JobRepository;
import com.sycho.spring.querydsl.repository.cross_join.SalesTeamMembersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrossJoinTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DevTeamMembersRepository devTeamMembersRepository;

    @Autowired
    SalesTeamMembersRepository salesTeamMembersRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void initDummyData() {
        var kakao = companyRepository.save(new Company("카카오"));
        var naver = companyRepository.save(new Company("네이버"));

        var kakao_dev_1 = devTeamMembersRepository.save(new DevTeamMember("카카오_개발자_1", kakao));
        var kakao_dev_2 = devTeamMembersRepository.save(new DevTeamMember("카카오_개발자_2", kakao));

        var naver_dev_1 = devTeamMembersRepository.save(new DevTeamMember("네이버_개발자_1", naver));
        var naver_dev_2 = devTeamMembersRepository.save(new DevTeamMember("네이버_개발자_2", naver));

        var kakao_sales_1 = salesTeamMembersRepository.save(new SalesTeamMembers("카카오_영업사원_1", kakao));
        var kakao_sales_2 = salesTeamMembersRepository.save(new SalesTeamMembers("카카오_영업사원_2", kakao));

        var naver_sales_1 = salesTeamMembersRepository.save(new SalesTeamMembers("네이버_영업사원_1", naver));
        var naver_sales_2 = salesTeamMembersRepository.save(new SalesTeamMembers("네이버_영업사원_2", naver));

        jobRepository.save(new Job("AWS EC2 인스턴스 생성", kakao_dev_1, null));
        jobRepository.save(new Job("AWS RDS 인스턴스 생성", kakao_dev_2, null));
        jobRepository.save(new Job("Spring Boot 프로젝트 생성", naver_dev_1, null));
        jobRepository.save(new Job("React 프로젝트 생성", naver_dev_2, null));

        jobRepository.save(new Job("매출목표 설정", null, kakao_sales_1));
        jobRepository.save(new Job("외근", null, kakao_sales_2));
        jobRepository.save(new Job("제품 소개서 만들기", null, naver_sales_1));
        jobRepository.save(new Job("월말 수금", null, naver_sales_2));
    }

    @Test
    void test() {

        var q = QJob.job;

//        var r = jpaQueryFactory
//                .select(
//                        q.id,
//                        new CaseBuilder()
//                                .when(q.devTeamMembers.isNotNull()).then(q.devTeamMembers.name)
//                                .otherwise(q.salesTeamMembers.name),
//                        new CaseBuilder()
//                                .when(q.devTeamMembers.isNotNull()).then(q.devTeamMembers.company.name)
//                                .otherwise(q.salesTeamMembers.company.name)
//                )
//                .from(q)
//                .fetch();

        var r = jpaQueryFactory
                .select(
                        q.id,
                        new CaseBuilder()
                                .when(q.devTeamMembers.isNotNull()).then(q.devTeamMembers.name)
                                .otherwise(q.salesTeamMembers.name),
                        new CaseBuilder()
                                .when(q.devTeamMembers.isNotNull()).then(q.devTeamMembers.company.name)
                                .otherwise(q.salesTeamMembers.company.name)
                )
                .from(q)
                .leftJoin(q.devTeamMembers).leftJoin(q.devTeamMembers.company)
                .leftJoin(q.salesTeamMembers).leftJoin(q.salesTeamMembers.company)
                .fetch();

        Assert.isTrue(r.size() == 8, "예상된 결과와 다릅니다.");
    }
}
