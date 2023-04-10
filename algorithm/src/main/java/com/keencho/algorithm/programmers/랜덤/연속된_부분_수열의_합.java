package com.keencho.algorithm.programmers.랜덤;

// https://school.programmers.co.kr/learn/courses/30/lessons/178870
public class 연속된_부분_수열의_합 {
    public int[] solution(int[] sequence, int k) {
        var digit = 1;
        while(true) {
            for (var i = 0; i < sequence.length; i ++) {
                var sum = doWork(sequence, digit, i, new StringBuilder());
                if (sum == k) {
                    return new int[] { i, i + digit - 1 };
                }
            }

            digit++;
        }
    }

    private int doWork(int[] sequence, int digit, int idx, StringBuilder sb) {
        if (sb.length() == digit) {
            return sb.chars().map(c -> c - '0').sum();
        }

        if (idx == sequence.length) return -1;

        sb.append(sequence[idx]);
        return doWork(sequence, digit, idx + 1, sb);
    }
}
