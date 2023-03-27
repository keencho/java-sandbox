package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략;

import com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.완전탐색.불량_사용자;

public class Main {
    public static void main(String[] args) {
        var q = new 불량_사용자();

        var s1 = new String[] { "frodo", "fradi", "crodo", "abc123", "frodoc" };
        var s2 = new String[] { "fr*d*", "*rodo", "******", "******" };
        var res = q.solution(s1, s2);

        System.out.println(res);
    }
}
