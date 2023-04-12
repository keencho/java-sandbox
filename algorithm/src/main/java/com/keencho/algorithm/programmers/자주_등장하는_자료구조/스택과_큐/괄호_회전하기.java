package com.keencho.algorithm.programmers.자주_등장하는_자료구조.스택과_큐;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/76502
public class 괄호_회전하기 {
    final Map<Character, Character> map = new HashMap<>();
    public int solution(String s) {
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');

        var result = 0;
        var count = 0;
        while(true) {
            if (isValid(s)) result ++;

            if (count == s.length() - 1) break;

            s = s.substring(1) + s.charAt(0);
            count++;
        }

        return result;
    }

    private boolean isValid(String s) {
        var stack = new Stack<>();

        for (var c : s.toCharArray()) {
            switch (c) {
                case '[', '(', '{' -> stack.push(c);
                default -> {
                    if (stack.isEmpty()) return false;
                    if (map.get(c) != stack.pop()) return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
