package com.keencho.algorithm.programmers_코딩테스트_문제풀이전략.완전탐색;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// p.224
// https://school.programmers.co.kr/learn/courses/30/lessons/64064
public class 불량_사용자 {

    Set<Set<String>> set = new HashSet<>();
    String[][] bans;

    private void doWork(int index, Set<String> banned) {
        if (index == bans.length) {
            set.add(new HashSet<>(banned));
            return;
        }

        for (var id : bans[index]) {
            if (banned.contains(id)) continue;

            banned.add(id);
            doWork(index + 1, banned);
            banned.remove(id);
        }
    }

    public int solution(String[] uids, String[] bids) {
        bans = Arrays.stream(bids)
                .map(bid -> Arrays.stream(uids)
                        .filter(uid -> uid.matches(bid.replace("*", ".")))
                        .toArray(String[]::new)
                ).toArray(String[][]::new);

        doWork(0, new HashSet<>());

        return set.size();
    }
}
