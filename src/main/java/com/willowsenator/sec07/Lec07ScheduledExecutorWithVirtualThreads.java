package com.willowsenator.sec07;

import com.willowsenator.sec07.externalservice.Client;
import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lec07ScheduledExecutorWithVirtualThreads {
    private static final Logger log = LoggerFactory.getLogger(Lec07ScheduledExecutorWithVirtualThreads.class);

    public static void main(String[] args) {
        scheduled();
    }

    // Schedules a task to run at a fixed rate
    private static void scheduled() {
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        try (scheduler; executor) {
            scheduler.scheduleAtFixedRate(
                    () -> executor.submit(() -> printProduct(1))
            , 0, 3, TimeUnit.SECONDS);

            CommonUtils.sleep(Duration.ofSeconds(15));
        }
    }

    private static String printProduct(int id) {
        var product = Client.getProduct(id);
        log.info("{} => {}", id, product);
        return product;
    }
}
