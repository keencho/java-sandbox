package com.keencho.algorithm.programmers.랜덤;

import java.util.ArrayList;
import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/152995
public class 인사고과 {
    public int solution(int[][] scores) {

        var wanHo = scores[0];

        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        int answer = 1, maxScore = 0, wanHoSum = wanHo[0] + wanHo[1];

        for (int[] score : scores) {
            // 내 앞에 동료평가점수가 나보다 높은사람이 한명이라도 있으면 탈락
            // 근무태도 동점자의 경우 동료평가 오름차순 하였으므로 고려하지 않아도 됨
            if (score[1] < maxScore) {
                // 탈락대상
                if (score.equals(wanHo))
                    return -1;
            } else {
                // 인센대상
                maxScore = Math.max(maxScore, score[1]);
                if (score[0] + score[1] > wanHoSum)
                    answer++;
            }
        }
        return answer;
    }
}
