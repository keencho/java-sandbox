package com.keencho.algorithm.programmers.코딩테스트_문제풀이전략_책.배열;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// p.60
public class 교점에_별_만들기 {
    public String[] solution(int[][] line) {
        var points = new ArrayList<Point>();

        for (var i = 0; i < line.length; i ++) {
            for(var j = i + 1; j < line.length; j ++) {
                var intersection = intersection(
                        line[i][0], line[i][1], line[i][2],
                        line[j][0], line[j][1], line[j][2]
                );

                if (intersection != null) {
                    points.add(intersection);
                }
            }
        }

        var min = getMinPoint(points);
        var max = getMaxPoint(points);

        var width = (int) (max.x - min.x + 1);
        var height = (int) (max.y - min.y + 1);

        // y축(행) 먼저 접근
        var arr = new char[height][width];
        for (var row : arr) {
            Arrays.fill(row, '.');
        }

        for (var p : points) {
            var x = (int) (p.x - min.x);
            var y = (int) (max.y - p.y);

            arr[y][x] = '*';
        }

        var result = new String[arr.length];
        for (var i = 0; i < result.length; i++) {
            result[i] = new String(arr[i]);
        }

        return result;


    }

    private Point intersection(long a1, long b1, long c1, long a2, long b2, long c2) {
        var x = (double) (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
        var y = (double) (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);

        if (x % 1 != 0 || y % 1 != 0) return null;

        return new Point((long) x, (long) y);
    }

    private Point getMinPoint(List<Point> list) {
        long x = Long.MAX_VALUE, y = Long.MAX_VALUE;

        for (var p : list) {
            if (p.x < x) x = p.x;
            if (p.y < y) y = p.y;
        }

        return new Point(x, y);
    }

    private Point getMaxPoint(List<Point> list) {
        long x = Long.MIN_VALUE, y = Long.MIN_VALUE;

        for (var p : list) {
            if (p.x > x) x = p.x;
            if (p.y > y) y = p.y;
        }

        return new Point(x, y);
    }

    private static class Point {
        public final long x, y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
