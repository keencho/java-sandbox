package com.keencho.spring.jpa.model;

import com.keencho.spring.jpa.listeners.SoccerTeamListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = SoccerTeamListeners.class)
@Table(name = "soccer_team")
public class SoccerTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
}
