package com.keencho.algorithm.programmers.자주_등장하는_자료구조.스택과_큐;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/42584
public class 주식_가격 {
    public int[] solution(int[] prices) {
        var result = new int[prices.length];
        var stack = new Stack<Integer>();

        for (var i = 0; i < prices.length; i ++) {
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                var pop = stack.pop();
                result[pop] = i - pop;
            }

            stack.add(i);
        }

        while(!stack.isEmpty()) {
            var pop = stack.pop();
            result[pop] = prices.length - pop - 1;
        }

        return result;
    }
}
