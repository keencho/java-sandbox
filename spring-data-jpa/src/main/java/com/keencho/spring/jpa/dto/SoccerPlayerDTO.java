package com.keencho.spring.jpa.dto;

import com.keencho.lib.spring.jpa.querydsl.KcQueryHandler;
import com.keencho.lib.spring.jpa.querydsl.annotation.KcQueryProjection;
import com.keencho.spring.jpa.model.Q;
import com.querydsl.core.types.Expression;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@KcQueryProjection
public class SoccerPlayerDTO {
    private Long id;
    private String name;
    private int height;
    private int weight;
    private Long teamId;
    private String teamName;

    public static final KcQSoccerPlayerDTO bindings;
    public static final KcQueryHandler queryHandler;

    static {
        var q = Q.soccerPlayer;

        bindings = KcQSoccerPlayerDTO.builder()
                .name(q.name)
                .height(q.height)
                .weight(q.weight)
                .teamName(q.soccerTeam.name)
                .teamId(q.soccerTeam.id)
                .build();

        queryHandler = query -> query.leftJoin(q.soccerTeam);
    }
}
