package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.자주_등장하는_자료구조.스택과_큐;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/12909
public class 올바른_괄호 {

    public boolean solution(String s) {

        var stack = new Stack<Character>();
        for (var c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

}
