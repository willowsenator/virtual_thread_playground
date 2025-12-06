package com.willowsenator.sec07;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;

public class Lec01AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(Lec01AutoCloseable.class);

    public static void main(String[] args) {
        /*var executor = Executors.newSingleThreadExecutor();
        executor.submit(() ->{
            task();
            logger.info("Submitted task");
        });
        executor.shutdown();*/

        try (var executor = Executors.newSingleThreadExecutor()) {
            executor.submit(() -> {
                task();
                log.info("Submitted task");
            });
        }
    }

    private static void task() {
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("Task done");
    }
}
