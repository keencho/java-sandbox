package com.keencho.algorithm.정렬;

import com.keencho.algorithm.Algorithm;

import java.util.Arrays;

@Algorithm("""
Selection Sort (선택 정렬)
    
- O(n^2) 시간복잡도
- 현재 위치에 들어갈 데이터를 찾아 선택하는 알고리즘
- 정렬의 대상이 되는 데이터 외에 추가적인 공간을 필요로 하지 않음
- '불안정 졍렬'임
""")
public class 선택 {
    public static void main(String[] args) {
        var array = new int[31];
        Arrays.fill(array, -1);

        for (var i = 0; i < array.length; i ++) {
            int t = -1;

            while (true) {
                int finalT = t;
                if (Arrays.stream(array).noneMatch(v -> v == finalT)) {
                    break;
                }

                t = (int) (Math.random() * 100);
            }
            array[i] = t;
        }

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
        // 마지막 인덱스룰 순회할때는 이미 가장 큰 값이 마지막 인덱스에 들어가 있을 것이므로 참조할 필요 없다.
        for (var i = 0; i < size - 1; i ++) {
            var idx = i;

            for (var j = i + 1; j < size; j ++) {
                if (a[j] < a[idx]) {
                    idx = j;
                }
            }

            swap(a, idx, i);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
