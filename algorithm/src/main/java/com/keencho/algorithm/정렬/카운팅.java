package com.keencho.algorithm.정렬;

import com.keencho.algorithm.utils.Utils;

/**
 * Counting Sort (계수 정렬 / 카운팅 정렬)
 *
 * - O(n) 시간복잡도
 * - 데이터의 값이 몇 번 나왔는지 세주는 방식
 * - 두 수를 비교하는 과정이 없기 떄문에 빠른 배치 가능
 * - 그러나 배열을 2개 사용해야 한다는 단점이 있기 때문에 원소의 크기가 늘어날수록 메모리 낭비가 커지게 된다.
 * - 퀵 정렬이나 병합정렬이 선호되는 이유도 위와 같음.
 */
public class 카운팅 {

    public static void main(String[] args) {
        var array = Utils.dummyArray(100, 31, true);    // 수열의 원소: 100개
        var counting = new int[31];                                                 // 수의 범위: 0 ~ 30
        var result = new int[100];                                                  // 정렬 결과 배열

        for (var i = 0; i < array.length; i ++) {
            array[i] = (int)(Math.random() * 31); // 0 ~ 30 사이의 값을 랜덤으로 배열에 할당
        }

        // 1. array의 value값을 index로 하는 counting 배열의 값 1 증가
        for (var i : array) {
            counting[i]++;
        }

        // 2. counting 배열 누적합
        for (var i = 1; i < counting.length; i ++) {
            counting[i] += counting[i - 1];
        }

        // 3. i번째 원소를 index로 하는 counting 배열의 값을 1 감소시킨 뒤 counting 배열의 값, 인덱스를 각각 result 배열의 인덱스, 값으로 하여 저장한다.
        // 안정적인 정렬을 위해 마지막 인덱스부터 진행한다.
        for (var i = array.length - 1; i >= 0; i--) {
            var index = array[i];
            counting[index] --;
            result[counting[index]] = index;
        }

        print("array", array);
        print("counting", counting);
        print("result", result);
    }

    private static void print(String name, int[] array) {
        System.out.print(name);
        for (var i = 0 ; i < array.length; i ++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(array[i] + "\t");
        }
        System.out.println("\n\n");
    }
}
