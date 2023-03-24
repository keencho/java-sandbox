package com.keencho.algorithm.programmers.배열;

// p.98
// https://school.programmers.co.kr/learn/courses/30/lessons/12949
// 행렬의 곱셈은 arr1의 행과 arr2의 열을 순서대로 곱한 값을 모두 더한 합이라고 생각하면 쉽다.
public class 행렬의_곱셈 {

    public int[][] solution(int[][] arr1, int[][] arr2) {
        var result = new int[arr1.length][arr2[0].length];

        for (var i = 0; i < result.length; i ++) {
            for (var j = 0; j < result[i].length; j ++) {
                for(var k = 0; k < arr2.length; k ++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return result;
    }
}
