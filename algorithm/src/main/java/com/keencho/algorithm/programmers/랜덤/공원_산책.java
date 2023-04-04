package com.keencho.algorithm.programmers.랜덤;

// https://school.programmers.co.kr/learn/courses/30/lessons/172928
public class 공원_산책 {
    public int[] solution(String[] park, String[] routes) {

        String[][] newPark = new String[park.length][park[0].length()];
        int[] coord = null;

        for (var y = 0; y < park.length; y ++) {
            var s = park[y].split("");
            for (var x = 0; x < s.length; x++) {
                if ("S".equals(s[x])) {
                    coord = new int[] { y, x };
                }

                newPark[y] = s;
            }
        }

        for (var i = 0; i < routes.length; i ++) {
            var target = routes[i].split(" ");
            var w = target[0];
            var c = Integer.parseInt(target[1]);

            var temp = new int[] { coord[0], coord[1] };
            var flag = true;
            for (var j = 0; j < c; j ++) {
                switch (w) {
                    case "N":
                        if (temp[0] != 0 && !newPark[temp[0] - 1][temp[1]].equals("X")) {
                            temp[0] = temp[0] - 1;
                        } else {
                            flag = false;
                        }
                        break;
                    case "S":
                        if (temp[0] != newPark.length - 1 && !newPark[temp[0] + 1][temp[1]].equals("X")) {
                            temp[0] = temp[0] + 1;
                        } else {
                            flag = false;
                        }
                        break;
                    case "W":
                        if (temp[1] != 0 && !newPark[temp[0]][temp[1] - 1].equals("X")) {
                            temp[1] = temp[1] - 1;
                        } else {
                            flag = false;
                        }
                        break;
                    case "E":
                        if (temp[1] != newPark[0].length - 1 && !newPark[temp[0]][temp[1] + 1].equals("X")) {
                            temp[1] = temp[1] + 1;
                        } else {
                            flag = false;
                        }
                        break;
                    default:
                        break;
                }
            }

            if (flag) {
                coord[0] = temp[0];
                coord[1] = temp[1];
            }
        }

        return coord;
    }
}
