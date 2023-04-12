package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.다시_볼것;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 동적 프로그래밍
// https://school.programmers.co.kr/learn/courses/30/lessons/1843
public class 사칙연산 {
    public int solution(String[] arr) {
        int[] nums = new int[(arr.length + 1) / 2];
        char[] ops = new char[arr.length / 2];
        for (int i = 0, j = 0; i < arr.length; i += 2, j++) {
            nums[j] = Integer.parseInt(arr[i]);
            if (i + 1 < arr.length) {
                ops[j] = arr[i + 1].charAt(0);
            }
        }
        List<Integer> results = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for (int i = 0; i < ops.length; i++) {
            if (ops[i] == '+') {
                stack.push(nums[i + 1]);
            } else {
                int num = nums[i + 1];
                char op = ops[i];
                int prev = stack.pop();
                while (!stack.isEmpty() && stack.peek() == '-') {
                    prev = -prev;
                    stack.pop();
                }
                stack.push(prev);
                stack.push((int) op);
                stack.push(num);
            }
        }
        while (!stack.isEmpty()) {
            int num = stack.pop();
            if (num == '-') {
                num = -stack.pop();
            }
            results.add(num);
        }
        int max = Integer.MIN_VALUE;
        dfs(results, 0, max);
        return max;
    }

    private void dfs(List<Integer> results, int index, int max) {
        if (index == results.size() - 1) {
            max = Math.max(max, results.get(index));
            return;
        }
        for (int i = index; i < results.size() - 1; i++) {
            List<Integer> copy = new ArrayList<>(results);
            int num1 = copy.remove(i);
            int num2 = copy.remove(i);
            int result = num1 + num2;
            copy.add(i, result);
            dfs(copy, i, max);
            copy.set(i, num1 - num2);
            dfs(copy, i, max);
        }
    }
}
