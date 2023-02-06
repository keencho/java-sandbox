package com.keencho.algorithm.정렬;

import com.keencho.algorithm.utils.Utils;

/**
 * Insertion Sort (삽입 정렬)
 *
 * - 현재 비교하고자 하는 타겟과 그 이전의 원소들과 비교하며 자리를 교환하는 정렬 방법
 * - '안정 정렬'
 */
public class 삽입 {

    public static void main(String[] args) {
        var array = Utils.dummyArray(31, 100, false);

        System.out.println("전");
        for (var i : array) {
            System.out.print(i + " ");
        }

        sort(array, array.length);

        System.out.println("\n");
        System.out.println("후");
        for (var i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void sort(int[] a, int size) {

        // 0부터 시작할 필요가 없다.
        for (var i = 1; i < size; i ++) {
            var target = a[i];

            var j = i - 1;

            // 타켓이 이전 원소보다 크기 전 때까지 반복
            while(j >= 0 && target < a[j]) {
                a[j + 1] = a[j];
                j--;
            }

            // 반복문에서 탈출하는 경우 앞의 원소가 타겟보다 작다는 것을 의미
            // 타겟 원소는 j 번째 원소 뒤에 와야함
            // 따라서 타겟은 j + 1 에 위치하게 된다.
            a[j + 1] = target;
        }
    }
}
