package com.keencho.spring.jpa.flush.repository;

import com.keencho.spring.jpa.flush.model.SoccerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoccerTeamRepository extends JpaRepository<SoccerTeam, Long> {
}
