package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.정렬;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// p.260
// https://school.programmers.co.kr/learn/courses/30/lessons/72411
public class 메뉴_리뉴얼 {

    final String KEY = "MAX_KEY";
    private void doWork(String word, String[] arr, int idx, int max, Map<Integer, Map<String, Integer>> map) {
        if (word.length() > 1) {
            var innerMap = map.get(word.length());
            if (innerMap == null) innerMap = new HashMap<>();

            var value = innerMap.containsKey(word) ? innerMap.get(word) + 1 : 1;
            var maxValue = innerMap.containsKey(KEY) ? Math.max(innerMap.get(KEY), value) : value;

            innerMap.put(word, value);
            innerMap.put(KEY, maxValue);
            map.put(word.length(), innerMap);
        }

        if (word.length() == max) return;

        for (var i = idx; i < arr.length; i ++) {
            doWork(word + arr[i], arr, i + 1, max, map);
        }
    }

    public String[] solution(String[] orders, int[] courses) {

        var maxCourse = courses[courses.length - 1];
        var map = new HashMap<Integer, Map<String, Integer>>();

        for (var order : orders) {
            var arr = order.chars().boxed().sorted().map(Character::toString).toArray(String[]::new);

            doWork("", arr, 0, maxCourse, map);
        }

        var list = new ArrayList<String>();
        for (var course : courses) {
            var innerMap = map.get(course);
            if (innerMap != null) {
                var maxValue = innerMap.get(KEY);
                innerMap.remove(KEY);

                if (maxValue > 1) {
                    for (var entry : innerMap.entrySet()) {
                        if (entry.getValue() == maxValue) {
                            list.add(entry.getKey());
                        }
                    }
                }
            }
        }

        return list.stream().sorted().map(String::valueOf).toArray(String[]::new);
    }
}
