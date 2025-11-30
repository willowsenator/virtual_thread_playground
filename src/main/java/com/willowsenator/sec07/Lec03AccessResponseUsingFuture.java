package com.willowsenator.sec07;

import com.willowsenator.sec07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Lec03AccessResponseUsingFuture {
    private static final Logger LOGGER = LoggerFactory.getLogger(Lec03AccessResponseUsingFuture.class);


    public static void main(String[] args) {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            var product1 = executor.submit(() -> Client.getProduct(1));
            var product2 = executor.submit(() -> Client.getProduct(2));
            var product3 = executor.submit(() -> Client.getProduct(3));

            LOGGER.info("product-1: {}", product1.get());
            LOGGER.info("product-2: {}", product2.get());
            LOGGER.info("product-3: {}", product3.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
