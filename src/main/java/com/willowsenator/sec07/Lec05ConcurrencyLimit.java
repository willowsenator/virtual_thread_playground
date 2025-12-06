package com.willowsenator.sec07;

import com.willowsenator.sec07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lec05ConcurrencyLimit {

    public static void main(String[] args) {
        //execute(Executors.newCachedThreadPool(), 20);
        var factory = Thread.ofVirtual().name("virtual", 1).factory();
        execute(Executors.newFixedThreadPool(5, factory), 20);
    }

    private static final Logger log = LoggerFactory.getLogger(Lec05ConcurrencyLimit.class);

    private static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 1; i < taskCount; i++) {
                int finalI = i;
                executorService.submit(() -> printProduct(finalI));
            }
        }

    }

    private static void printProduct(int id) {
        log.info("{} => {}", id, Client.getProduct(id));
    }
}
