package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.완전탐색;

import java.util.ArrayList;
import java.util.StringTokenizer;

// p.202
// https://school.programmers.co.kr/learn/courses/30/lessons/67257
public class 수식_최대화 {

    char[][] cases = new char[][] {
            new char[] { '*', '-', '+' },
            new char[] { '*', '+', '-' },
            new char[] { '+', '-', '*' },
            new char[] { '+', '*', '-' },
            new char[] { '-', '+', '*' },
            new char[] { '-', '*', '+' }
    };

    private long calc (long one, long two, char operator) {
        return switch (operator) {
            case '+' -> one + two;
            case '*' -> one * two;
            case '-' -> one - two;
            default -> 0;
        };
    }

    public long solution(String expression) {

        var numbers = new ArrayList<Long>();
        var operators = new ArrayList<Character>();
        var s = new StringBuilder();

        // 나는 아래와 같이 직접 문자 배열로 만들어서 값을 저장했지만
        // 책에서는 new StringTokenizer(expression, "+-*", true) 로 값을 저장한다. 이게 더 좋은 방식인듯?
        var ca = expression.toCharArray();
        for (var i = 0; i < ca.length; i++) {
            var ex = ca[i];
            if (Character.isDigit(ex)) {
                s.append(ex);

                if (i == ca.length - 1) {
                    numbers.add(Long.parseLong(s.toString()));
                }
            } else {
                numbers.add(Long.parseLong(s.toString()));
                s.setLength(0);
                operators.add(ex);
            }
        }

        var max = Long.MIN_VALUE;
        for (var c1 : cases) {
            var list = new ArrayList<>(numbers);
            var operatorList = new ArrayList<>(operators);

            for (var c2 : c1) {
                var idx = 0;
                while(idx <= operatorList.size() - 1) {
                    var operator = operatorList.get(idx);

                    if (operator == c2) {
                        list.set(idx, calc(list.get(idx), list.get(idx + 1), operator));
                        list.remove(idx + 1);
                        operatorList.remove(idx);
                        idx = 0;
                        continue;
                    }

                    idx ++;
                }
            }

            max = Math.max(Math.abs(list.get(0)), max);
        }

        return max;
    }
}
