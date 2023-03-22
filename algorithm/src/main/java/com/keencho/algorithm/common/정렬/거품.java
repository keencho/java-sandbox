package com.keencho.algorithm.common.정렬;

import com.keencho.algorithm.common.utils.Utils;

/**
 * Bubble Sort (거품 정렬)
 *
 * - 두 개의 인접한 원소를 비교하여 정렬하는 알고리즘
 * - O(N^2) 의 복잡도
 * - 안정졍렬
 */
public class 거품 {
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
        for (var i = 1; i < size; i ++) {
            var swap = false;

            for (var j = 0; j < size - i; j ++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j , j + 1);
                    swap = true;
                }
            }

            if (!swap) break;
        }
    }

    private static void swap(int[] a, int i, int j) {
        var temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
