package com.keencho.algorithm.programmers.랜덤;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/178871
public class 달리기_경주 {
    String[] players;
    Map<String, Integer> map = new HashMap<>();
    public String[] solution(String[] players, String[] callings) {
        this.players = players;

        var currentCaller = callings[0];
        var count = 1;
        for (var i = 1; i < callings.length; i ++) {
            if (currentCaller.equals(callings[i])) {
                count++;
                continue;
            }
            shift(currentCaller, count);

            currentCaller = callings[i];
            count = 1;
        }

        shift(currentCaller, count);

        return this.players;
    }

    private void shift(String player, int sIdx) {
        int idx = -1;

        if (map.containsKey(player)) {
            idx = map.get(player);
        } else {
            for (var i = 0; i < this.players.length; i ++) {
                if (player.equals(this.players[i])){
                    idx = i;
                    break;
                }
            }
        }

        for (var i = idx; i > idx - sIdx; i --) {
            this.players[i] = this.players[i - 1];
            map.put(this.players[i], i);
        }

        this.players[idx - sIdx] = player;
        map.put(player, idx - sIdx);
    }
}
