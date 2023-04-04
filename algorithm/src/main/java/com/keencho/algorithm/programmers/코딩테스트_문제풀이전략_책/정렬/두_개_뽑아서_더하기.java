package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.정렬;

import java.util.HashSet;

// p.240
// https://school.programmers.co.kr/learn/courses/30/lessons/68644
public class 두_개_뽑아서_더하기 {
    public int[] solution(int[] numbers) {
        var set = new HashSet<Integer>();

        for (var i = 0; i < numbers.length; i ++) {
            for (var j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream().sorted().mapToInt(v -> v).toArray();
    }
}
