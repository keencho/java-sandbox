package com.keencho.spring.jpa.flush.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "soccer_team")
public class SoccerTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;
}
