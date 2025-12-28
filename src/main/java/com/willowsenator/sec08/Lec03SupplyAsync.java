package com.willowsenator.sec08;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec03SupplyAsync {
    private static final Logger logger = LoggerFactory.getLogger(Lec03SupplyAsync.class);
    public static void main(String[] args) {
        logger.info("Main started");
        //var cf = fastTask();
        var cf = slowTask();
        cf.thenAccept(v-> logger.info("Value received: {}", v));

        //logger.info("Result: {}", cf.join());
        logger.info("Main ended");
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> slowTask() {
        logger.info("Starting slow task");

        var cf = CompletableFuture.supplyAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            return "hello";
        }, Executors.newVirtualThreadPerTaskExecutor());

        logger.info("Slow task initiated");
        return cf;
    }
}
