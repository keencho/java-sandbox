package com.keencho.algorithm.common.utils;

import java.util.Arrays;

public class Utils {

    public static int[] dummyArray(int size, int range, boolean allowDuplicate) {
        var array = new int[size];
        Arrays.fill(array, -1);

        for (var i = 0; i < array.length; i ++) {
            int t;

            if (allowDuplicate) {
                t = (int) (Math.random() * range);
            } else {
                t = -1;
                while (true) {
                    int finalT = t;
                    if (Arrays.stream(array).noneMatch(v -> v == finalT)) {
                        break;
                    }

                    t = (int) (Math.random() * range);
                }
            }

            array[i] = t;
        }

        return array;
    }
}
