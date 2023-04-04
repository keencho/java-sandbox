package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.재귀;

import java.util.ArrayList;
import java.util.List;

// p.180
// https://school.programmers.co.kr/learn/courses/30/lessons/84512
public class 모음_사전 {

    private char[] chars = new char[] { 'A', 'E', 'I', 'O', 'U' };
    private List<String> list = new ArrayList<>();

    private void generate(String word) {
        list.add(word);

        if (word.length() == 5) return;

        for (var c : chars) {
            generate(word + c);
        }
    }

    public int solution(String word) {
        generate("");
        return list.indexOf(word);
    }
}
