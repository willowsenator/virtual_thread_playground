package com.willowsenator.sec06;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

public class Lec01ThreadFactory {
    private static final Logger logger = LoggerFactory.getLogger(Lec01ThreadFactory.class);

    public static void main(String[] args) {
        demo(Thread.ofVirtual().name("virtual-", 1).factory());
        CommonUtils.sleep(Duration.ofSeconds(3));
    }

    private static void demo(ThreadFactory factory) {
        for (int i = 0; i < 3; i++) {
            var t = factory.newThread(() -> {
                logger.info("Task started: {}", Thread.currentThread());
                var ct = factory.newThread(() -> {
                    logger.info("Child Task started: {}", Thread.currentThread());
                    CommonUtils.sleep(Duration.ofSeconds(2));
                    logger.info("Child Task finished: {}", Thread.currentThread());
                });
                ct.start();
                logger.info("Task finished: {}", Thread.currentThread());
            });
            t.start();
        }
    }
}
