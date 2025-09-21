package com.willowsenator.sec01;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void ioIntensiveTask(int i) {
        try {
            log.info("starting ioIntensiveTask {}. Thread info: {}", i, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(10));
            log.info("completed ioIntensiveTask {}. Thread info: {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
