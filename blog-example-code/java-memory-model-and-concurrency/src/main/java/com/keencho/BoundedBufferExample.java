package com.keencho;

public class BoundedBufferExample {
    public static void main(String[] args) throws Exception {
        BoundedBuffer buffer = new BoundedBuffer();

        // 생산자 스레드 생성
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    buffer.put(i); // 값을 버퍼에 추가
                    System.out.println("Produced: " + i);
                    Thread.sleep(500); // 잠시 대기
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 소비자 스레드 생성
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    int value = buffer.take(); // 값을 버퍼에서 가져옴
                    System.out.println("Consumed: " + value);
                    Thread.sleep(1000); // 잠시 대기
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 생산자 스레드와 소비자 스레드 시작
        producerThread.start();
        consumerThread.start();

        // 생산자 스레드와 소비자 스레드 종료 대기
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
