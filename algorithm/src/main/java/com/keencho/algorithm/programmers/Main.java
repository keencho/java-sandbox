package com.keencho.algorithm.programmers;

import com.keencho.algorithm.programmers.랜덤.공원_산책;

public class Main {

    public static void main(String[] args) {

        var q = new 공원_산책();
        var res = q.solution(new String[]{ "OSO", "OOO", "OXO", "OOO" }, new String[]{ "E 2", "S 3", "W 1" });

        System.out.println(res[0]);
        System.out.println(res[1]);
    }

}
