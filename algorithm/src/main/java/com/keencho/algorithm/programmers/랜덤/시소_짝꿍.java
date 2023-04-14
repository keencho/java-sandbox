package com.keencho.algorithm.programmers.랜덤;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/152996
public class 시소_짝꿍 {
    public long solution(int[] weights) {
        var list = new ArrayList<Integer>();
        var map = new HashMap<Integer, Integer>();

        Arrays.sort(weights);

        for (var weight : weights) {
            if (map.containsKey(weight)) {
                map.put(weight, map.get(weight) + 1);
            } else {
                list.add(weight);
                map.put(weight, 1);
            }
        }

        var count = 0L;
        for (var i = 0; i < list.size(); i ++) {
            for (var j = i + 1; j < list.size(); j ++) {
                var num1 = list.get(i);
                var num2 = list.get(j);

                if (num1 * 4 == num2 * 3 || num1 * 4 == num2 * 2 || num1 * 3 == num2 * 2) {
                    count += (long) map.get(num1) * map.get(num2);
                }
            }
        }

        for (var entry : map.entrySet()) {
            var value = entry.getValue();

            if (value > 1) {
                count += ((long) value * (value - 1)) / 2 ;
            }
        }

        return count;
    }
}
