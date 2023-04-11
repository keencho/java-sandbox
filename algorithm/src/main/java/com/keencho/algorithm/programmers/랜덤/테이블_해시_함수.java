package com.keencho.algorithm.programmers.랜덤;

import java.util.Arrays;
import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/147354
public class 테이블_해시_함수 {

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (v1, v2) -> {
            var c1 = v1[col - 1];
            var c2 = v2[col - 1];
            if (c1 == c2) {
                return v2[0] - v1[0];
            }

            return c1 - c2;
        });

        return IntStream.range(row_begin - 1, row_end)
                .mapToObj(idx -> {
                    var arr = data[idx];
                    var sum = 0;
                    for (var i : arr) {
                        sum += i % (idx + 1);
                    }
                    return sum;
                })
                .reduce((a, b) -> a ^ b)
                .orElse(0);
    }
}
