package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.동적_프로그래밍;

import java.util.Arrays;

// TODO: 효율성 테스트
// https://school.programmers.co.kr/learn/courses/30/lessons/42898
public class 등굣길 {
    int x;
    int y;
    boolean[][] puddles;
    int[][] mem = new int[101][101];

    public int solution(int x, int y, int[][] puddles) {
        this.x = x;
        this.y = y;
        this.puddles = new boolean[y + 1][x + 1];
        for (var m : mem) {
            Arrays.fill(m, -1);
        }
        for (var puddle : puddles) {
            this.puddles[puddle[1]][puddle[0]] = true;
        }

        return calc(1, 1);
    }

    private int calc(int x, int y) {
        if (x > this.x || y > this.y) return 0;
        if (this.puddles[y][x]) return 0;

        if (this.mem[y][x] != -1) return mem[y][x];
        if (x == this.x && y == this.y) return 1;

        return this.mem[y][x] = (calc(x + 1, y) + calc(x, y + 1)) % 1000000007;
    }
}
