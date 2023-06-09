package com.keencho;

import java.util.Arrays;

public class ParallelStreamsExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int sum = Arrays.stream(numbers)
                .parallel()
                .sum();

        System.out.println("Sum: " + sum);
    }
}
