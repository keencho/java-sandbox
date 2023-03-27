package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.문자열;

// p.107
// https://school.programmers.co.kr/learn/courses/30/lessons/12932
public class 자연수_뒤집어_배열로_만들기 {
    public int[] solution(long n) {
        var str = Long.toString(n);
        var arr = new int[str.length()];

        for (var i = 0; i < arr.length; i ++) {
            arr[i] = str.charAt((arr.length - 1) - i) - '0';
        }
        return arr;
    }

}
