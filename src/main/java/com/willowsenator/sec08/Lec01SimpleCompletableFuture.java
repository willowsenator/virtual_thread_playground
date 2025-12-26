package com.willowsenator.sec08;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Lec01SimpleCompletableFuture {
    private static final Logger logger = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    public static void main(String[] args) {
        logger.info("Main started");
        //var cf = fastTask();
        var cf = slowTask();
        cf.thenAccept(v-> logger.info("Value received: {}", v));

        //logger.info("Result: {}", cf.join());
        logger.info("Main ended");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> fastTask() {
        logger.info("Starting fast task");
        var cf = new CompletableFuture<String>();
        cf.complete("hi");
        logger.info("Fast task completed");
        return cf;
    }

    private static CompletableFuture<String> slowTask() {
        logger.info("Starting slow task");
        var cf = new CompletableFuture<String>();
        Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            cf.complete("hello");
        });
        logger.info("Slow task initiated");
        return cf;
    }
}
