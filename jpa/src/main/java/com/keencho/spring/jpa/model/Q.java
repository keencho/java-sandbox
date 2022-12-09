package com.keencho.spring.jpa.model;

import com.querydsl.core.types.Path;

import java.util.HashMap;
import java.util.Map;

public class Q {
    public static QSoccerPlayer soccerPlayer = QSoccerPlayer.soccerPlayer;
    public static QSoccerTeam soccerTeam = QSoccerTeam.soccerTeam;

    public static Map<Path<?>, Object> newUpdateMap() {
        return new HashMap<>();
    }
}
