package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.문자열;

// p.110
// https://school.programmers.co.kr/learn/courses/30/lessons/12926
public class 시저_암호 {
    public String solution(String s, int n) {
        var arr = s.toCharArray();

        var sb = new StringBuilder();

        for (char value : arr) {
            if (!Character.isAlphabetic(value)) {
                sb.append(value);
                continue;
            }

            var ct = Character.isUpperCase(value) ? 'A' : 'a';
            var position = value - ct;
            position = (position + n) % ('z' - 'a' + 1);

            sb.append((char) (ct + position));
        }

        return sb.toString();
    }

}
