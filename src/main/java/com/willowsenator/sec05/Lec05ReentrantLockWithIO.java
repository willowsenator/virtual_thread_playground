package com.willowsenator.sec05;

import com.willowsenator.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lec05ReentrantLockWithIO {
    private static final Logger log = LoggerFactory.getLogger(Lec05ReentrantLockWithIO.class);


    private static final Lock lock = new ReentrantLock();


    static {
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

    public static void main(String[] args) {

        Runnable runnable = () -> log.info("** TEST MESSAGE **");

        var builder = Thread.ofVirtual();
        demo(builder);
        Thread.ofVirtual().start(runnable);

        CommonUtils.sleep(Duration.ofSeconds(15));
    }

    private static void demo(Thread.Builder builder){
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started: {}", Thread.currentThread());
                ioTask();
                log.info("Task finished: {}", Thread.currentThread());
            });
        }
    }
    private static void ioTask(){
        try {
            lock.lock();
            CommonUtils.sleep(Duration.ofSeconds(10));
        } catch (Exception e) {
            log.error("Exception in inMemoryTask", e);
        } finally {
            lock.unlock();
        }
    }
}
