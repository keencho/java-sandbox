package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.완전탐색;

import java.util.stream.IntStream;

// p.192
// https://school.programmers.co.kr/learn/courses/30/lessons/42840
public class 모의고사 {
    private final int[][] rules = new int[][] {
            new int[] { 1,2,3,4,5 },
            new int[] { 2,1,2,3,2,4,2,5 },
            new int[] { 3,3,1,1,2,2,4,4,5,5 }
    };

    public int[] solution(int[] answers) {
        var corrects = new int[3];

        var maxCorrect = Integer.MIN_VALUE;
        for (var i = 0; i < answers.length; i++) {
            for (var j = 0; j < rules.length; j++) {
                var submittedAnswer = rules[j][i % rules[j].length];
                if (answers[i] == submittedAnswer) corrects[j] ++;

                maxCorrect = Math.max(corrects[j], maxCorrect);
            }
        }

        int finalMaxCorrect = maxCorrect;
        return IntStream.range(0, 3).filter(idx -> corrects[idx] == finalMaxCorrect).map(i ->i + 1).toArray();
    }


}
