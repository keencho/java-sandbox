package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.완전탐색;

import java.util.HashSet;

// p.211
// https://school.programmers.co.kr/learn/courses/30/lessons/42839
public class 소수_찾기 {

    int[] arr;
    HashSet<Integer> set = new HashSet<>();

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (var i = 2; i <= Math.sqrt(n); i ++) {
            if (n % i == 0) return false;
        }

        return true;
    }

    void generate(int num, boolean[] used) {
        if (isPrime(num)) set.add(num);

        for (var i = 0; i < arr.length; i ++) {
            if (used[i]) continue;
            used[i] = true;
            generate(num * 10 + arr[i], used);
            used[i] = false;
        }
    }

    public int solution(String numbers) {
        arr = numbers.chars().map(c -> c - '0').toArray();
        generate(0, new boolean[arr.length]);

        return set.size();
    }
}
