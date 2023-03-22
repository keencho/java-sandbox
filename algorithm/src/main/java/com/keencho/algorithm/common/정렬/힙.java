package com.keencho.algorithm.common.정렬;

import com.keencho.algorithm.common.utils.Utils;

/**
 * Heap Sort (힙 정렬)
 *
 * -
 */
public class 힙 {
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
        /*
         * 부모노드와 heaify과정에서 음수가 발생하면 잘못 된 참조가 발생하기 때문에
         * 원소가 1개이거나 0개일 경우는 정렬 할 필요가 없으므로 바로 함수를 종료한다.
         */
        if(size < 2) {
            return;
        }

        /*
         * left child node = index * 2 + 1
         * right child node = index * 2 + 2
         * parent node = (index - 1) / 2
         */

        // 가장 마지막 요소의 부모 인덱스
        int parentIdx = getParent(size - 1);

        // max heap
        for(int i = parentIdx; i >= 0; i--) {
            heapify(a, i, size - 1);
        }


        for(int i = size - 1; i > 0; i--) {

            /*
             *  root인 0번째 인덱스와 i번째 인덱스의 값을 교환해준 뒤
             *  0 ~ (i-1) 까지의 부분트리에 대해 max heap을 만족하도록
             *  재구성한다.
             */
            swap(a, 0, i);
            heapify(a, 0, i - 1);
        }
    }

    private static int getParent(int child) {
        return (child - 1) / 2;
    }

    private static void swap(int[] a, int i, int j) {
        var temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void heapify(int[] a, int parentIdx, int lastIdx) {

        int leftChildIdx;
        int rightChildIdx;
        int largestIdx;

        /*
         * 현재 부모 인덱스의 자식 노드 인덱스가
         * 마지막 인덱스를 넘지 않을 때 까지 반복한다.
         *
         * 이 때 왼쪽 자식 노드를 기준으로 해야 한다.
         * 오른쪽 자식 노드를 기준으로 범위를 검사하게 되면
         * 마지막 부모 인덱스가 왼쪽 자식만 갖고 있을 경우
         * 왼쪽 자식노드와는 비교 및 교환을 할 수 없기 때문이다.
         */
        while((parentIdx * 2) + 1 <= lastIdx) {
            leftChildIdx = (parentIdx * 2) + 1;
            rightChildIdx = (parentIdx * 2) + 2;
            largestIdx = parentIdx;

            /*
             * left child node와 비교
             * (범위는 while문에서 검사했으므로 별도 검사 필요 없음)
             */
            if (a[leftChildIdx] > a[largestIdx]) {
                largestIdx = leftChildIdx;
            }

            /*
             * right child node와 비교
             * right child node는 범위를 검사해주어야한다.
             */
            if (rightChildIdx <= lastIdx && a[rightChildIdx] > a[largestIdx]) {
                largestIdx = rightChildIdx;
            }

            /*
             * 교환이 발생했을 경우 두 원소를 교체 한 후
             * 교환이 된 자식노드를 부모 노드가 되도록 교체한다.
             */
            if (largestIdx != parentIdx) {
                swap(a, parentIdx, largestIdx);
                parentIdx = largestIdx;
            } else {
                return;
            }
        }
    }
}
