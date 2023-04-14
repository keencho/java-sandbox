package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.자주_등장하는_자료구조.스택과_큐;

import java.util.ArrayList;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583
public class 다리를_지나는_트럭 {
    private class Info {
        int weight;
        int seconds;

        public Info(int weight, int seconds) {
            this.weight = weight;
            this.seconds = seconds;
        }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        var idx = 0;
        var seconds = 0;
        var movingTrucks = new ArrayList<Info>();
        var count = 0;

        while(true) {
            var firstMovingTruck = movingTrucks.stream().findFirst().orElse(null);
            if (firstMovingTruck != null && seconds - firstMovingTruck.seconds == bridgeLength) {
                movingTrucks.remove(0);
                count ++;
            }

            if (count == truckWeights.length) break;
            var target = truckWeights[idx];
            if (movingTrucks.stream().mapToInt(i -> i.weight).sum() + target <= weight && movingTrucks.size() < bridgeLength) {
                movingTrucks.add(new Info(target, seconds));
                if (idx < truckWeights.length - 1) {
                    idx ++;
                }

            }

            seconds++;

        }

        return seconds + 1;
    }
}
