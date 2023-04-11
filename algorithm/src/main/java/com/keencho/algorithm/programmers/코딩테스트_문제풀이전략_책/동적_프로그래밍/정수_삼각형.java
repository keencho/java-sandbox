package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.동적_프로그래밍;

import java.util.Arrays;

public class 정수_삼각형 {
    int[][] arr = new int[501][501];
    public int solution(int[][] triangle) {
        for (var item : arr) {
            Arrays.fill(item, -1);
        }

        return max(0, 0, triangle);
    }

    private int max(int x, int y, int[][] triangle) {
        if (y == triangle.length) return 0;
        if (arr[x][y] != -1) return arr[x][y];
        return arr[x][y] = triangle[y][x] + Math.max(max(x, y + 1, triangle), max(x + 1, y + 1, triangle));
    }
}
