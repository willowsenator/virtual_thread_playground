package com.willowsenator.sec08;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec02RunAsync {
    private static final Logger log = LoggerFactory.getLogger(Lec02RunAsync.class);
    public static void main(String[] args) {
        log.info("Main started");
        runAsync().thenRun(() -> log.info("Callback executed"))
                .exceptionally(ex -> {
                    log.info("error occurred: {}", ex.getMessage());
                    return null;
                });
        log.info("Main ended");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<Void> runAsync() {
        log.info("Method starts");
        var cf = CompletableFuture.runAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            //log.info("Inside runAsync");
            throw new RuntimeException("Oops!");
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("Method ends");
        return cf;
    }
}
