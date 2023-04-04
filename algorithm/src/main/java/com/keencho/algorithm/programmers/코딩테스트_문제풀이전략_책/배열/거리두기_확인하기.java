package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.배열;

// p.86
// P: 사람
// O: 빈 테이블
// X: 파티션
// https://programmers.co.kr/learn/courses/30/lessons/81302
// https://github.com/gilbutITbook/080337/blob/main/3%EC%9E%A5/%EA%B1%B0%EB%A6%AC%EB%91%90%EA%B8%B0_%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0.java
public class 거리두기_확인하기 {

    private final int[] dx = { 0, -1, 0, 1, -2, -1, 1, 2, -1, 0, 1, 0 };
    private final int[] dy = { -2, -1, -1, -1, 0, 0, 0, 0, 1, 1, 1, 2 };

    public int[] solution(String[][] places) {
        var result = new int[places.length];

        for (var i = 0; i < places.length; i ++) {
            result[i] = isPass(places[i]) ? 1 : 0;
        }

        return result;
    }

    private boolean isPass(String[] place) {
        var last = 4;

        var x = 0;
        var y = 0;

        while (y != last || x != last) {

            if (place[y].charAt(x) == 'P') {
                for (var i = 0; i < dx.length; i++) {
                    var cx = x - dx[i];
                    var cy = y - dy[i];

                    if (cy < 0 || cx < 0 || cy > last || cx > last) {
                        continue;
                    }

                    if (place[cy].charAt(cx) == 'P') {
                        // 가로
                        if (cy == y) {
                            if (place[cy].charAt(Math.max(cx, x) - 1) != 'X') {
                                return false;
                            }
                        }
                        // 세로
                        else if (cx == x) {
                            if (place[Math.max(cy, y) - 1].charAt(cx) != 'X') {
                                return false;
                            }
                        }
                        // 대각
                        else {
                            if (place[y].charAt(cx) != 'X' || place[cy].charAt(x) != 'X') {
                                return false;
                            }
                        }
                    }
                }
            }

            // x 커서가 끝에 도달
            if (x == last) {
                x = 0;
                y++;
                continue;
            }

            x++;

        }

        return true;
    }
}
