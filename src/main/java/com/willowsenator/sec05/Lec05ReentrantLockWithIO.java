package com.willowsenator.sec05;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lec05ReentrantLockWithIO {
    private static final Logger logger = LoggerFactory.getLogger(Lec05ReentrantLockWithIO.class);


    private static final Lock lock = new ReentrantLock();


    static {
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

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
    private static void ioTask(){
        try {
            lock.lock();
            CommonUtils.sleep(Duration.ofSeconds(10));
        } catch (Exception e) {
            logger.error("Exception in inMemoryTask", e);
        } finally {
            lock.unlock();
        }
    }
}
