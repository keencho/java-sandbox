package com.keencho.algorithm.programmers.랜덤;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/160586
public class 대충_만든_자판 {
    public int[] solution(String[] keymap, String[] targets) {

        var map = new HashMap<Character, Integer>();
        for (var i = 0; i < keymap.length; i ++) {
            var key = keymap[i];
            var ca = key.toCharArray();
            for (var j = 0; j < ca.length; j ++) {
                var c = ca[j];
                if (map.containsKey(c)) {
                    map.put(c, Math.min(map.get(c), j + 1));
                } else {
                    map.put(c, j + 1);
                }
            }
        }

        var res = new int[targets.length];
        for (var i = 0; i < targets.length; i ++) {
            var ca = targets[i].toCharArray();
            var notExist = false;
            var tmp = 0;
            for (var j = 0; j < ca.length; j ++) {
                if (map.get(ca[j]) == null) {
                    res[i] = -1;
                    notExist = true;
                    break;
                }

                tmp += map.get(ca[j]);
            }
            if (!notExist) res[i] = tmp;
        }

        return res;
    }
}
