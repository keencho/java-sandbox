package com.keencho.algorithm.programmers.못푼것;

// https://school.programmers.co.kr/learn/courses/30/lessons/181187
public class 두_원_사이의_정수_쌍 {
    public long solution(int r1, int r2) {
        return (count(r2) - count(r1)) + (((r2 - r1) + 1) * 4L);
    }

    private long count(long r) {
        var count = 0;
        for (var x = 1; x < r; x++) {
            count += (int)Math.floor(Math.sqrt(r * r - (long) x * x));
        }
        return count * 4L;
    }
}
