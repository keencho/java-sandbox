package com.keencho.algorithm.programmers.문자열;

// p.137
// https://school.programmers.co.kr/learn/courses/30/lessons/12916
public class 문자열_내_p와_y의_개수 {
    public boolean solution(String s) {
        s = s.toLowerCase();

        var l1 = s.length() - s.replace("p", "").length();
        var l2 = s.length() - s.replace("y", "").length();

        return l1 == l2;
    }
}
