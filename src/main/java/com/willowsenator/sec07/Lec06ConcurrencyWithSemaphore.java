package com.willowsenator.sec07;

import com.willowsenator.sec07.concurrencylimit.ConcurrencyLimiter;
import com.willowsenator.sec07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

public class Lec06ConcurrencyWithSemaphore {

    public static void main(String[] args) throws Exception {
        var factory = Thread.ofVirtual().name("virtual", 1).factory();
        var concurrencyLimiter = new ConcurrencyLimiter(
                Executors.newThreadPerTaskExecutor(factory), 3);
        execute(concurrencyLimiter, 20);
    }

    private static final Logger log = LoggerFactory.getLogger(Lec06ConcurrencyWithSemaphore.class);

    private static void execute(ConcurrencyLimiter concurrencyLimiter, int taskCount) throws Exception {
        try (concurrencyLimiter) {
            for (int i = 1; i <= taskCount; i++) {
                int finalI = i;
                concurrencyLimiter.submit(() -> printProduct(finalI));
            }
            log.info("Submitted all tasks");
        }

    }

    private static String printProduct(int id) {
        var product = Client.getProduct(id);
        log.info("{} => {}", id, product);
        return product;
    }
}
