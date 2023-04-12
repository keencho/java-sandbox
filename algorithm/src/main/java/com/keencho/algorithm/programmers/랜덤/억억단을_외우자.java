package com.keencho.algorithm.programmers.랜덤;

import java.util.Arrays;

public class 억억단을_외우자 {
    public int[] solution(int e, int[] starts) {
        var arr = new int[e + 1];

        for (var i = 1; i <= e; i++) {
            for (var j = 1; i * j <= e; j++) {
                arr[i * j] += 1;
            }
        }

        var max = new int[e + 1];
        Arrays.fill(max, e);
        for (var i = e - 1; i > 0; i --) {
            if (arr[i] >= arr[max[i + 1]]) {
                max[i] = i;
            } else {
                max[i] = max[i + 1];
            }
        }

        var result = new int[starts.length];
        for (var i = 0; i < starts.length; i ++) {
            result[i] = max[starts[i]];
        }

        return result;
    }
}
