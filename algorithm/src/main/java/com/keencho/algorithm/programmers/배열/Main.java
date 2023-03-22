package com.keencho.algorithm.programmers.배열;

public class Main {
    public static void main(String[] args) {
        var q = new 거리두기_확인하기();
        var s = new String[][] { } ;
        s[0][0] = "POOOP";
        s[0][1] = "OXOOX";
        s[0][2] = "OPXPX";
        s[0][3] = "OOXOX";
        s[0][4] = "POXXP";

        var result = q.solution(s);

        System.out.println(result);
    }
}
