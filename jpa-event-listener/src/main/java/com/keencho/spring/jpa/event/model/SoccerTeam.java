package com.keencho.spring.jpa.event.model;

import com.keencho.spring.jpa.event.SoccerTeamEventListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(SoccerTeamEventListener.class)
@Table(name = "soccer_team")
public class SoccerTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String teamName;

    String homeTown;

    String managerName;

    LocalDate foundingDate;

    LocalDate lastMatchDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("teamName: ").append(teamName).append("\n");
        sb.append("homeTown: ").append(homeTown).append("\n");
        sb.append("managerName: ").append(managerName).append("\n");
        sb.append("foundingDate: ").append(foundingDate).append("\n");
        sb.append("lastMatchDate: ").append(lastMatchDate).append("\n");
        sb.append("\n");

        return sb.toString();
    }


}
