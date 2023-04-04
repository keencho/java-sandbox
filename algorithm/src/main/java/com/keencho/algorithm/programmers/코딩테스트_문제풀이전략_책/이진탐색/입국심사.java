package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.이진탐색;

// p.298
// https://school.programmers.co.kr/learn/courses/30/lessons/43238
public class 입국심사 {

    public long solution(int target, int[] arr) {
        long start = 0L;
        long end = 1000000000L * 1000000000L;

        long sum = 0L;
        while(end > start) {
            sum = 0L;
            long mid = (start + end) / 2L;

            for(var num : arr) {
                sum += mid / num;
            }

            if (sum >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }
}
