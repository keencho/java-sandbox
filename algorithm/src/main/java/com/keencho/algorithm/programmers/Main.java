package com.keencho.algorithm.programmers;

import com.keencho.algorithm.programmers.랜덤.인사고과;

public class Main {

    public static void main(String[] args) {

        var q = new 인사고과();

        var p = new int[][] {
                new int[] { 2, 2 },
                new int[] { 1, 4 },
                new int[] { 3, 2 },
                new int[] { 3, 2 },
                new int[] { 2, 1 }
        };

        var res = q.solution(p);
    }

}
