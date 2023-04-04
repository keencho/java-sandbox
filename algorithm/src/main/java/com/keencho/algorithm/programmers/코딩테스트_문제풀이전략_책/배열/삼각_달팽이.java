package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.배열;

// p.72
// 개량 ver --> https://github.com/gilbutITbook/080337/blob/main/3%EC%9E%A5/%EC%82%BC%EA%B0%81_%EB%8B%AC%ED%8C%BD%EC%9D%B4_%EA%B0%9C%EB%9F%89.java
public class 삼각_달팽이 {
    public int[] solution(int n) {

        var arr = new int[n][n];

        int x = 0, y = 0;

        // 1 = down, 2 = right, 3 = diagonal
        var type = 1;
        var num = 1;
        while (x != n && y != n && arr[y][x] == 0) {

            arr[y][x] = num;

            if (type == 1 && (y + 1 == n || arr[y + 1][x] != 0)) {
                type = 2;
            } else if (type == 2 && (x + 1 == n || arr[y][x + 1] != 0) ) {
                type = 3;
            } else if (type == 3 && arr[y - 1][x - 1] != 0) {
                type = 1;
            }

            if (type == 1) {
                y += 1;
            } else if (type == 2) {
                x += 1;
            } else if (type == 3) {
                x -= 1;
                y -= 1;
            }

            num++;
        }

        var result = new int[num - 1];

        var c = 0;
        for (var item : arr) {
            for (var item2 : item) {
                if (item2 != 0) {
                    result[c] = item2;
                    c++;
                }
            }
        }
        return result;
    }
}
