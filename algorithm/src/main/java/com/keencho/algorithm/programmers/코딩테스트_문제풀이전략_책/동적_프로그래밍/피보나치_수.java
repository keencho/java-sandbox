package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.동적_프로그래밍;

import java.math.BigInteger;

public class 피보나치_수 {
    public int solution(int n) {
        var arr = new BigInteger[n + 1];

        arr[0] = new BigInteger("0");
        arr[1] = new BigInteger("1");

        for (var i = 2; i < arr.length; i ++) {
            arr[i] = arr[i - 2].add(arr[i - 1]);
        }

        return arr[n].remainder(new BigInteger("1234567")).intValue();
    }
}
