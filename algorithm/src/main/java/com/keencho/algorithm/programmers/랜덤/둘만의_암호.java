package com.keencho.algorithm.programmers.랜덤;

import java.util.Set;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/155652
public class 둘만의_암호 {
    int index;
    Set<Integer> skipSet;
    public String solution(String s, String skip, int index) {
        this.index = index;
        this.skipSet = skip.chars().boxed().collect(Collectors.toSet());
        return s.chars().mapToObj(ch -> String.valueOf(this.getChar((char) ch))).collect(Collectors.joining());
    }

    private char getChar(char character) {
        int count = 0;
        int c = character;
        while (count != index) {
            if (c == 122) c = 96;
            c += 1;

            if (skipSet.contains(c)) {
                continue;
            }

            count ++;
        }
        return (char) c;
    }
}
