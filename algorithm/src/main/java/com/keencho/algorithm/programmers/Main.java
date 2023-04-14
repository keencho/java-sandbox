package com.keencho.algorithm.programmers;

import com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.자주_등장하는_자료구조.그래프와_트리.순위;

public class Main {

    public static void main(String[] args) {

        var q = new 순위();

        var res = q.solution(
                5,
                new int[][] {
                        new int[] { 4, 3 },
                        new int[] { 4, 2 },
                        new int[] { 3, 2 },
                        new int[] { 1, 2 },
                        new int[] { 2, 5 }
                }
        );

        System.out.println(res);
    }

}
