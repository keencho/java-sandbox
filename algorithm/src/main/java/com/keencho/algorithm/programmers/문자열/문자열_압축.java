package com.keencho.algorithm.programmers.문자열;

import java.util.ArrayList;

// p.120
// https://school.programmers.co.kr/learn/courses/30/lessons/60057
public class 문자열_압축 {
    public int solution(String s) {
        var min = Integer.MAX_VALUE;

        for (var i = 1; i <= s.length(); i ++) {
            min = Math.min(getLength(s, i), min);
        }

        return min;
    }

    private int getLength(String word, int length) {
        var list = new ArrayList<String>();

        for (var start = 0; start < word.length(); start += length) {
            var end = start + length;
            if (end > word.length()) {
                end = word.length();
            }

            list.add(word.substring(start, end));
        }

        var sb = new StringBuilder();
        String currentWord = "";
        var count = 1;
        for (var i = 0; i < list.size(); i ++) {
            var value = list.get(i);

            if (value.equals(currentWord)) {
                count ++;
            } else {
                sb.append(count == 1 ? "" : count).append(currentWord);
                count = 1;
            }
            currentWord = list.get(i);
        }

        sb.append(count == 1 ? "" : count).append(currentWord);

        return sb.length();
    }

}
