package com.keencho.spring.jpa.model;

import com.keencho.spring.jpa.listeners.SoccerPlayerListeners;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@EntityListeners(value = SoccerPlayerListeners.class)
@Table(name = "soccer_player")
public class SoccerPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @ManyToOne
    SoccerTeam soccerTeam;

    int age;

    int height;

    int weight;

    @Override
    public String toString() {
        return String.format("name = %s / soccerTeamName = %s", name, soccerTeam != null ? soccerTeam.getName() : null);
    }

    public SoccerPlayer(String name, SoccerTeam soccerTeam) {
        this.name = name;
        this.soccerTeam = soccerTeam;
    }
}
