package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.해시;

import java.util.HashMap;

// p.333
// https://school.programmers.co.kr/learn/courses/30/lessons/42576
public class 완주하지_못한_선수 {
    public String solution(String[] participant, String[] completion) {
        var map = new HashMap<String, Integer>();

        for (var c : completion) {
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }

        for (var p : participant) {
            var c = map.get(p);
            if (c == null) return p;

            if (c == 1) map.remove(p);
            else map.put(p, c - 1);
        }

        return null;
    }
}
