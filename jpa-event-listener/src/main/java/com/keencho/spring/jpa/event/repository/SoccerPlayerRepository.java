package com.keencho.spring.jpa.event.repository;

import com.keencho.spring.jpa.event.model.SoccerPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoccerPlayerRepository extends JpaRepository<SoccerPlayer, Long> {
}
