package com.keencho.spring.jpa.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "soccer_player")
public class SoccerPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    String age;

    @Enumerated(EnumType.STRING)
    SoccerPosition position;

    @ManyToOne(fetch = FetchType.LAZY)
    private SoccerTeam soccerTeam;
}
