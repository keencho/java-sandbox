package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.이진탐색;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// p.285
// https://school.programmers.co.kr/learn/courses/30/lessons/72412
public class 순위_검색 {

    Map<String, List<Integer>> map = new HashMap<>();

    private void doWork(int idx, int score, String prefix, String[] arr) {
        if (idx == arr.length - 1) {
            map.putIfAbsent(prefix, new ArrayList<>());
            map.get(prefix).add(score);
            return;
        }

        doWork(idx + 1, score, prefix + arr[idx], arr);
        doWork(idx + 1, score, prefix + "-", arr);
    }

    private void doPostProcess(String[] info) {
        for (var s : info) {
            var split = s.split(" ");
            var score = Integer.parseInt(split[split.length - 1]);

            doWork(0, score, "", split);
        }

        map.forEach((key, value) -> Collections.sort(value));
    }

    private int bs(List<Integer> list, int score) {
        var start = 0;
        var end = list.size() - 1;

        while(end > start) {
            var mid = (start + end) / 2;

            if (list.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        if (list.get(start) < score) {
            return list.size();
        }

        return start;
    }

    public int[] solution(String[] info, String[] query) {
        doPostProcess(info);

        var result = new int[query.length];

        for (var i = 0; i < query.length; i ++) {
            var split = query[i].split(" (and )?");
            var newQuery = String.join("", Arrays.copyOf(split, split.length - 1));
            var item = map.get(newQuery);

            if (item != null) {
                result[i] = item.size() - bs(item, Integer.parseInt(split[split.length - 1]));
            }
        }

        return result;
    }
}
