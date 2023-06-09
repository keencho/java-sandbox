package com.keencho;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeCollectionExample {
    public static void main(String[] args) {
        var map = new ConcurrentHashMap<String, Integer>();

        // 다수의 스레드 생성 / 시작
        var threads = new Thread[5];
        for (var i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Worker(map));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the final contents of the map
        for (var key : map.keySet()) {
            var value = map.get(key);
            System.out.println(key + ": " + value);
        }
    }

    static class Worker implements Runnable {
        private final ConcurrentHashMap<String, Integer> map;

        public Worker(ConcurrentHashMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            // Perform concurrent operations on the map
            for (var i = 0; i < 100; i++) {
                var key = "Key" + i;
                map.putIfAbsent(key, i);
                map.computeIfPresent(key, (key1, value) -> value + 1);
            }
        }
    }
}
