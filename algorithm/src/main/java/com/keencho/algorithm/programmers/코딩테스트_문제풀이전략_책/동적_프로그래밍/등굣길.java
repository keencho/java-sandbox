package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.동적_프로그래밍;

// TODO: 효율성 테스트
// https://school.programmers.co.kr/learn/courses/30/lessons/42898
public class 등굣길 {
    long count = 0;
    int[][] puddles;

    int call = 0;
    public int solution(int x, int y, int[][] puddles) {
        this.puddles = puddles;
        calc(1, 1, new int[y][x]);

        System.out.println(call);

        return (int) (count % 10000000007L);
    }

    private void calc(int x, int y, int[][] arr) {
        call ++;

        for (var item : this.puddles) {
            if (item[0] == x && item[1] == y) {
                return;
            }
        }

        if (y == arr.length && x == arr[0].length) {
            count ++;
            return;
        }

        if (x == arr[0].length) {
            calc(x, y + 1, arr);
            return;
        } else if (y == arr.length) {
            calc(x + 1, y, arr);
            return;
        }

        calc(x, y + 1, arr);
        calc(x + 1, y, arr);
    }
}
