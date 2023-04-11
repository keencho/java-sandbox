package com.keencho.algorithm.programmers.랜덤;

// 투포인터
// https://school.programmers.co.kr/learn/courses/30/lessons/178870
public class 연속된_부분_수열의_합 {
    public int[] solution(int[] sequence, int k) {
        var p1 = 0;
        var p2 = 0;

        var sum = 0;
        var right = true;
        int[] result = null;
        while (true) {
            if (right) {
                sum += sequence[p2];
            } else {
                sum -= sequence[p1 - 1];
            }

            if (sum == k) {
                if (result == null) {
                    result = new int[] { p1, p2 };
                } else {
                    var original = result[1] - result[0];
                    var neww = p2 - p1;

                    if (neww < original) {
                        result = new int[] { p1, p2 };
                    }
                }
            }

            if (p1 == sequence.length - 1 && p1 == p2) {
                break;
            }

            if (p2 == sequence.length - 1){
                p1++;
                right = false;
            } else {
                if (sum <= k) {
                    p2++;
                    right = true;
                } else {
                    p1++;
                    right = false;
                }
            }
        }

        return result;
    }
}
