package com.willowsenator.sec07;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lec02ExecutorServiceTypes {
    private static final Logger log = LoggerFactory.getLogger(Lec02ExecutorServiceTypes.class);

    public static void main(String[] args) {
        // execute(Executors.newSingleThreadExecutor(), 3);
        //execute(Executors.newFixedThreadPool(5), 20);
        //execute(Executors.newCachedThreadPool(), 200);
        //execute(Executors.newVirtualThreadPerTaskExecutor(), 10_000);
        scheduled();
    }

    // Schedules a task to run at a fixed rate
    private static void scheduled() {
        try (var executorService = Executors.newSingleThreadScheduledExecutor()) {
            executorService.scheduleAtFixedRate(
                    () -> log.info("Executing task..."), 0, 1, TimeUnit.SECONDS
            );
            CommonUtils.sleep(Duration.ofSeconds(5));
        }
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 0; i < taskCount; i++) {
                int finalI = i;
                executorService.submit(() -> ioTask(finalI));
            }
        }

    }

    private static void ioTask(int i) {
        log.info("Task started: {}, Thread info {}", i, Thread.currentThread().getName());
        CommonUtils.sleep(Duration.ofSeconds(5));
        log.info("Task completed: {}, Thread info {}", i, Thread.currentThread().getName());
    }
}
