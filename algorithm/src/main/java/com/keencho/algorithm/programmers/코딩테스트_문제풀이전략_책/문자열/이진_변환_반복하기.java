package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.문자열;

// p.131
// https://school.programmers.co.kr/learn/courses/30/lessons/70129
public class 이진_변환_반복하기 {
    private int zero = 0;
    private int count = 0;
    private String word;

    public int[] solution(String s) {
        word = s;

        while (!"1".equals(word)) {
            doWork();
        }

        return new int[]{ count, zero };
    }

    private void doWork() {
        var sb = new StringBuilder();
        for (var i = 0; i < word.length(); i ++) {
            var value = word.charAt(i);
            if (value == '0') {
                zero ++;
                continue;
            }

            sb.append(value);
        }

        count ++;
        word = Integer.toBinaryString(sb.length());
    }
}
