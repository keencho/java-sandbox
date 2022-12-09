package com.keencho.spring.jpa.repository;

import com.keencho.lib.spring.jpa.querydsl.repository.KcJpaRepository;
import com.keencho.spring.jpa.model.SoccerPlayer;

import java.util.Optional;

public interface SoccerPlayerRepository extends KcJpaRepository<SoccerPlayer, Long> {
    Optional<SoccerPlayer> findByName(String name);
}
