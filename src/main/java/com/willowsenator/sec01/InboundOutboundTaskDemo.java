package com.willowsenator.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {
    private static final int MAX_THREADS = 10;
    private static final int MAX_VIRTUAL = 20;

    public static void main(String[] args) throws InterruptedException {
        //virtualThreadDemo();
        //platformThreadDemo1();
        virtualThreadDemo();
    }

    /**
     * Platform threads demo
     */
    private static void platformThreadDemo1() {
        for (int i = 0; i < MAX_THREADS; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> Task.ioIntensiveTask(finalI));
            thread.start();
        }
    }

    /**
     * Platform threads demo with builder
     */
    private static void platformThreadDemo2() {
        var builder = Thread.ofPlatform().name("willownsenator",1);
        for (int i = 0; i < MAX_THREADS; i++) {
            int finalI = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensiveTask(finalI));
            thread.start();
        }
    }


    /**
     * Platform threads demo with builder and latch
     */
    private static void platformThreadDemo3() throws InterruptedException {
        var latch = new CountDownLatch(MAX_THREADS);
        var builder = Thread.ofPlatform().name("daemon",1).daemon();
        for (int i = 0; i < MAX_THREADS; i++) {
            int finalI = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensiveTask(finalI);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }

    /**
     * Virtual threads demo
     * virtual threads are daemon by default
     */
    private static void virtualThreadDemo() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofVirtual();
        for (int i = 0; i < MAX_VIRTUAL; i++) {
            int finalI = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensiveTask(finalI);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}
