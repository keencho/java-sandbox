package com.keencho.spring.jpa.event.repository;

import com.keencho.spring.jpa.event.model.SoccerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoccerTeamRepository extends JpaRepository<SoccerTeam, Long> {
    Optional<SoccerTeam> findByTeamName(String teamName);
}
