package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.정렬;

import java.util.Arrays;

// p.243
// https://school.programmers.co.kr/learn/courses/30/lessons/42747
public class H_Index {
    public int solution(int[] arr) {
        Arrays.sort(arr);

        for (var i = arr.length; i >= 1; i--) {
            if (arr[arr.length - i] >= i) return i;
        }
        return 0;
    }
}
