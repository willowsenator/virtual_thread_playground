package com.willowsenator.sec06;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Lec02ThreadDemo {
    private static final Logger logger = LoggerFactory.getLogger(Lec02ThreadDemo.class);

    public static void main(String[] args) throws InterruptedException {
        //isVirtual();
        //join();
        interrupt();
    }

    /**
     * Check if a thread is virtual or platform
     */

    private static void isVirtual() {
        var t1 = Thread.ofVirtual().start(() -> CommonUtils.sleep(Duration.ofSeconds(2)));
        var t2 = Thread.ofPlatform().start(() -> CommonUtils.sleep(Duration.ofSeconds(2)));

        logger.info("t1 is virtual: {}", t1.isVirtual());
        logger.info("t2 is virtual: {}", t2.isVirtual());
        logger.info("Is current thread virtual: {}", Thread.currentThread().isVirtual());
    }

    private static void join() throws InterruptedException {
        var t1 = Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(2));
            logger.info("Called product service");
        });

        var t2 = Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            logger.info("Called email service");
        });

        t1.join();
        t2.join();
    }

    private static void interrupt() throws InterruptedException {
        var t1 = Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(2));
            logger.info("Called product service");
        });
        logger.info("Is interrupted: {}", t1.isInterrupted());
        t1.interrupt();
        logger.info("Is interrupted: {}", t1.isInterrupted());
    }
}
