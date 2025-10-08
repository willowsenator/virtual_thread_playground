package com.willowsenator.sec05;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec03SynchronizationWithIO {
    private static final Logger logger = LoggerFactory.getLogger(Lec03SynchronizationWithIO.class);

    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        Runnable runnable = () -> logger.info("** TEST MESSAGE **");

        var builder = Thread.ofVirtual();
        demo(builder);
        Thread.ofVirtual().start(runnable);

        CommonUtils.sleep(Duration.ofSeconds(15));
    }

    private static void demo(Thread.Builder builder){
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                logger.info("Task started: {}", Thread.currentThread());
                ioTask();
                logger.info("Task finished: {}", Thread.currentThread());
            });
        }
    }
    private static synchronized void ioTask(){
        list.add(1);
        CommonUtils.sleep(Duration.ofSeconds(10));
    }
}
