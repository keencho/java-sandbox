package com.keencho.algorithm.programmers;

import com.keencho.algorithm.programmers.랜덤.대충_만든_자판;
import com.keencho.algorithm.programmers.랜덤.연속된_부분_수열의_합;

public class Main {

    public static void main(String[] args) {

//        var q = new 연속된_부분_수열의_합();
//
//        var p1 = new int[] { 2, 2, 2, 2, 2};
//        var p2 = 6;
//
//        var res = q.solution(p1, p2);
//
//        for (int re : res) {
//            System.out.println(re);
//        }

        var q = new 대충_만든_자판();

        var p1 = new String[] { "ABACD", "BCEFD" };
        var p2 = new String[] { "ABCD","AABB" };

        q.solution(p1, p2);
    }

}
