package com.keencho.spring.jpa.repository;

import com.keencho.lib.spring.jpa.querydsl.repository.KcJpaRepository;
import com.keencho.spring.jpa.model.SoccerTeam;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoccerTeamRepository extends KcJpaRepository<SoccerTeam, Long> {
    Optional<SoccerTeam> findByName(String name);
}
