package com.keencho.algorithm.programmers.랜덤;

// 누적합
// https://school.programmers.co.kr/learn/courses/30/lessons/161988
public class 연속_펄스_부분_수열의_합 {
    public long solution(int[] sequence) {
        var max = 0L;
        var min = 0L;
        var arr = new long[sequence.length + 1];

        for (var i = 1; i < arr.length; i ++) {
            arr[i] = arr[i - 1] + ( sequence[i - 1] * ( i % 2 == 0 ? 1L : -1L ));

            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        return Math.abs(max - min);
    }
}
