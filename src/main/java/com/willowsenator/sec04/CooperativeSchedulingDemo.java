package com.willowsenator.sec04;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CooperativeSchedulingDemo {
    private static final Logger logger = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);

    static {
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
    }

    public static void main(String[] args) {

        var builder = Thread.ofVirtual();

        var t1 = builder.unstarted(() -> demo(1));
        var t2 = builder.unstarted(() -> demo(2));
        var t3 = builder.unstarted(() -> demo(3));
        t1.start();
        t2.start();
        t3.start();

        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static void demo(int threadNumber){
        logger.info("threat-{} started", threadNumber);
        for (int i = 0; i < 10; i++) {
            logger.info("thread-{} is printing {}. Thread: {}", threadNumber, i, Thread.currentThread());
            if((threadNumber == 1 && i % 2 == 0) || threadNumber == 2) {
                Thread.yield();
            }
        }
        logger.info("threat-{} ended", threadNumber);
    }
}
