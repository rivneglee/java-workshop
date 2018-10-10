package com.stardust.concurrency.java5;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class _CyclicBarrier {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        Runnable command = () -> {
            System.out.println("Preparing task");
            try {
                Thread.sleep(1000);
                cyclicBarrier.await();
                System.out.println("Task Started");
                countDownLatch.countDown();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println("First try");
        IntStream.range(0, 5).forEach(i -> executorService.execute(command));

        countDownLatch.await();

        System.out.println("Second try");
        cyclicBarrier.reset();
        IntStream.range(0, 5).forEach(i -> executorService.execute(command));
    }
}
