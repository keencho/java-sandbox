package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.해시;

import java.util.HashSet;

// p.331
// https://school.programmers.co.kr/learn/courses/30/lessons/86051
public class 없는_숫자_더하기 {
    public int solution(int[] numbers) {
        var set = new HashSet<Integer>();

        for (var num : numbers) {
            set.add(num);
        }

        var res = 0;
        for (var i = 1; i <= 9; i ++) {
            if (!set.contains(i)) res += i;
        }

        return res;
    }
}
