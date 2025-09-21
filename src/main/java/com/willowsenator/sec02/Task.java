package com.willowsenator.sec02;


import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void execute(int i) {
        log.info("starting task {}. Thread info: {}", i, Thread.currentThread());
        try {
            method1(i);
        } catch (Exception e) {
            log.error("Exception in execute for value {}: {}", i, e.getMessage());
        } finally {
            log.info("completed task {}. Thread info: {}", i, Thread.currentThread());
        }
    }

    private static void method1(int i) {
        CommonUtils.sleep(Duration.ofMillis(10));
        try {
            method2(i);
        } catch (Exception e) {
            log.error("Exception in method1 for value {}: {}", i, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void method2(int i) {
        CommonUtils.sleep(Duration.ofMillis(100));
        method3(i);
    }

    private static void method3(int i) {
        CommonUtils.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("Invalid value: " + i);
        }
    }
}
