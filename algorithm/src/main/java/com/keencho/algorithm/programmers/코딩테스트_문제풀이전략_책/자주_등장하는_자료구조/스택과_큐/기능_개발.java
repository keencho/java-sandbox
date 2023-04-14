package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.자주_등장하는_자료구조.스택과_큐;

import java.util.ArrayList;

// https://school.programmers.co.kr/learn/courses/30/lessons/42586
public class 기능_개발 {

    public int[] solution(int[] progresses, int[] speeds) {
        var currentNum = (int) Math.ceil((double) (100 - progresses[0]) / speeds[0]);
        var list = new ArrayList<Integer>();
        var count = 1;

        for (var i = 1; i < progresses.length; i ++) {
            var num = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);

            if (currentNum < num) {
                list.add(count);
                count = 1;
                currentNum = num;
                continue;
            }

            count ++;
        }
        list.add(count);

        return list.stream().mapToInt(i -> i).toArray();
    }
}
