package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략;

import com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.이진탐색.징검다리;

public class Main {

    public static void main(String[] args) {

        var q = new 징검다리();
        var res = q.solution(
                25,
                new int[] { 2, 14, 11, 21, 17 },
                2
        );

        System.out.println(res);
    }

}
