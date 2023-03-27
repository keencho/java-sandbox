package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.재귀;

// p.162
// https://school.programmers.co.kr/learn/courses/30/lessons/68936
public class 쿼드압축_후_개수_세기 {
    private static class Count {
        public int zero;
        public int one;

        public Count(int zero, int one) {
            this.zero = zero;
            this.one = one;
        }

        public Count add(Count count) {
            return new Count(this.zero + count.zero, this.one + count.one);
        }
    }

    public int[] solution(int[][] arr) {
        var count = count(0, 0, arr.length, arr);
        return new int[] { count.zero, count.one };
    }

    private Count count(int x, int y, int size, int[][] arr) {
        int height = size / 2;

        for (var i = 0; i < x + size; i ++) {
            for (var j = 0; j < y + size; j ++) {
                if (arr[j][i] != arr[y][x]) {
                    return count(x, y, height, arr)
                            .add(count(x + height, y, height, arr))
                            .add(count(x, y + height, height, arr))
                            .add(count(x + height, y + height, height, arr));
                }
            }
        }

        if (arr[y][x] == 1) {
            return new Count(0, 1);
        }

        return new Count(1, 0);
    }

}
