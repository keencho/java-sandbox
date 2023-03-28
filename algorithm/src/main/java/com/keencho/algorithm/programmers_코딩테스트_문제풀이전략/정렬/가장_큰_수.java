package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.정렬;

import java.util.Arrays;
import java.util.stream.Collectors;

// p.257
// https://school.programmers.co.kr/learn/courses/30/lessons/42746
public class 가장_큰_수 {
    public String solution(int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((v1, v2) -> Integer.parseInt(v2 + v1) - Integer.parseInt(v1 + v2))
                .collect(Collectors.joining())
                .replaceAll("^0+", "0");
    }
}
