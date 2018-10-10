package com.stardust.concurrency.java5;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class _CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        Runnable command = () -> {
            System.out.println("Preparing task");
            try {
                Thread.sleep(1000);
                System.out.println("Task Started");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        IntStream.range(0, 5).forEach(i -> executorService.execute(command));

        countDownLatch.await();

        System.out.println("All tasks completed");
    }
}
