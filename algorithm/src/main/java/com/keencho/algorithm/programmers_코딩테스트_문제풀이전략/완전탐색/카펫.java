package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.완전탐색;

// p.198
// https://school.programmers.co.kr/learn/courses/30/lessons/42842
public class 카펫 {
    public int[] solution(int brown, int yellow) {
        for (var i = 1 ; i <= Math.floor(Math.sqrt(brown + yellow)); i ++) {
            var d = (brown + yellow) / i;

            if ((d - 2) * (i - 2) == yellow) {
                return new int[] { d, i };
            }
        }

        return null;
    }
}
