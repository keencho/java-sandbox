package com.keencho.spring.jpa.model;

import com.keencho.spring.jpa.listeners.SoccerTeamListeners;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(value = SoccerTeamListeners.class)
@Table(name = "soccer_team")
public class SoccerTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    public SoccerTeam(String name) {
        this.name = name;
    }
}
