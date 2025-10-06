package com.willowsenator.sec03;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);


    public static void cpuIntensiveTask(int i) {
        //log.info("starting cpuIntensiveTask. Thread info: {}", Thread.currentThread());
        var timeTaken = CommonUtils.timer(() -> findFib(i));
        //log.info("ending cpuIntensiveTask. Time taken: {} ms. Thread info: {}", timeTaken, Thread.currentThread());
    }

    // 2^n time complexity
    public static long findFib(long input) {
        if (input < 2 ) return input;
        return findFib(input - 1) + findFib(input - 2);
    }
}
