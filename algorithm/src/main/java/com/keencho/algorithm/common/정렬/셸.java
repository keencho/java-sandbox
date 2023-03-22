package com.keencho.algorithm.common.정렬;

import com.keencho.algorithm.common.utils.Utils;

/**
 * Shell Sort(셸 정렬)
 *
 * - 두 개의 인접한 원소를 비교하여 정렬하는 알고리즘
 * - O(N^2) 의 복잡도
 * - 안정졍렬
 */
public class 셸 {

    private final static int[] gap = {
            1, 4, 10, 23, 57, 132, 301, 701, 1750, 3937,
            8858, 19930, 44842, 100894, 227011, 510774,
            1149241, 2585792, 5818032, 13090572, 29453787,
            66271020, 149109795, 335497038, 754868335, 1698453753
    };

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
        int index = getGap(size);

        for (int i = index; i >= 0; i--) {

            for (int j = 0; j < gap[i]; j++) {
                insertion_sort(a, j, size, gap[i]);
            }
        }
    }

    private static void insertion_sort(int[] a, int start, int size, int gap) {
        for (int i = start + gap; i < size; i += gap) {

            int target = a[i];
            int j = i - gap;

            while (j >= start && target < a[j]) {
                a[j + gap] = a[j];
                j -= gap;
            }

            a[j + gap] = target;

        }
    }

    private static int getGap(int length) {
        var index = 0;

        var len = (int) (length / 2.25);

        while(gap[index] < len) {
            index ++;
        }

        return index;
    }
}
