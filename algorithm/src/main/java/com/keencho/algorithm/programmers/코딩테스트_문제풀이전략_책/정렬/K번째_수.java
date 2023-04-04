package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.정렬;

import java.util.Arrays;

// p.237
// https://school.programmers.co.kr/learn/courses/30/lessons/42748
public class K번째_수 {
    public int[] solution(int[] array, int[][] commands) {
        var result = new int[commands.length];

        for (var idx = 0; idx < commands.length; idx ++) {
            var command = commands[idx];
            var i = command[0];
            var j = command[1];
            var k = command[2];

            var t = Arrays.copyOfRange(array, i - 1, j);

            Arrays.sort(t);

            result[idx] = t[k - 1];
        }

        return result;
    }
}
