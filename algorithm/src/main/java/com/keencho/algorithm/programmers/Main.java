package com.keencho.algorithm.programmers;

public class Main {

    public static void main(String[] args) {

        System.out.println(new Main().solution(92));
    }

    public int solution(int storey) {
        var answer = 0;

        while (storey > 0) {
            storey = storey / 10;
            var remain = storey % 10;

            if (remain > 5 || (remain == 5 && storey % 10 >= 5)) {
                remain = 10 - remain;
                storey += 1;
            }

            answer += remain;
        }

        return answer;
    }

}
