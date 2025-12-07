package com.willowsenator.sec07;

import com.willowsenator.sec07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Gatherers;
import java.util.stream.IntStream;

// JDK 24+
public class Lec08MapConcurrent {
    private static final Logger log = LoggerFactory.getLogger(Lec08MapConcurrent.class);

    public static void main(String[] args) {
        // ConcurrentHashMap
        var list = IntStream.rangeClosed(1, 50)
                .boxed()
                .gather(Gatherers.mapConcurrent(50, Lec08MapConcurrent::getProductName))
                .toList();

        log.info("size: {}", list.size());
    }


    private static String getProductName(int id) {
        var product = Client.getProduct(id);
        log.info("{} => {}", id, product);
        return product;
    }
}
