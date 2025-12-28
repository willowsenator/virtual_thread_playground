package com.willowsenator.sec08.aggregator;

import com.willowsenator.sec07.externalservice.Client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class AggregatorService {
    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService){
        this.executorService = executorService;
    }

    public ProductDTO getProductDto(int id) {
        var product = CompletableFuture.supplyAsync(() -> Client.getProduct(id), executorService)
                .exceptionally(ex -> "Product not found");
        var rating = CompletableFuture.supplyAsync(() -> Client.getRating(id), executorService)
                .exceptionally(ex -> -1)
                .orTimeout(750, TimeUnit.MICROSECONDS)
                .exceptionally(ex -> -2);

        return new ProductDTO(id, product.join(), rating.join());
    }
}
