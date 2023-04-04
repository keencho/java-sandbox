package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.문자열;

// p.128
// https://school.programmers.co.kr/learn/courses/30/lessons/68935
public class Three진법_뒤집기 {
    public int solution(int n) {
        return Integer.parseInt(new StringBuilder(Integer.toString(n, 3)).reverse().toString(), 3);
    }
}
