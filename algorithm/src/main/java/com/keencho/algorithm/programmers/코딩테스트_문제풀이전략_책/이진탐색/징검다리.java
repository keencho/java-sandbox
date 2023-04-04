package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.이진탐색;

import java.util.Arrays;

// p.303
// https://school.programmers.co.kr/learn/courses/30/lessons/43236
// 제출안함 --> 문제 이해 안됨;
public class 징검다리 {

    private void doWork(int distance, int[] rocks, int n) {

    }

    public int solution(int distance, int[] rocks, int n) {
        rocks = Arrays.copyOf(rocks, rocks.length + 1);
        rocks[rocks.length - 1] = distance;

        Arrays.sort(rocks);

        var start = 1;
        var end = distance + 1;

        while (end - start > 1) {
            var d = (start + end) / 2;

            var removed = 0;
            var last = 0;
            for (var rock : rocks) {
                if (rock - last < d) {
                    removed++;
                    continue;
                }
                last = rock;
            }

            if (removed <= n) {
                start = d;
            } else {
                end = d;
            }
        }

        return start;
    }
}
