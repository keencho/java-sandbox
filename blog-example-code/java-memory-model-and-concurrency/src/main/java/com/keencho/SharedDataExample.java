package com.keencho;

public class SharedDataExample {
    public static void main(String[] args) {
        var sharedData = new SharedData();

        var t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedData.increment();
            }
        });

        var t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedData.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + sharedData.getCounter());
    }
}
