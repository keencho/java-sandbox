package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.문자열;

// p.114
// https://school.programmers.co.kr/learn/courses/30/lessons/12930
public class 이상한_문자_만들기 {
    public String solution(String s) {
        var upper = true;
        var arr = s.toCharArray();

        var sb = new StringBuilder();
        for (var i = 0; i < arr.length; i ++) {
            var value = arr[i];
            if (!Character.isAlphabetic(value)) {
                sb.append(value);
                upper = true;
                continue;
            }

            sb.append(upper ? Character.toUpperCase(value) : Character.toLowerCase(value));

            upper = !upper;

        }

        return sb.toString();
    }
}
