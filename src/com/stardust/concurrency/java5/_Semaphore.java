package com.stardust.concurrency.java5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class _Semaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        Runnable command = () -> {
            try {
                System.out.println("Getting semaphore");
                semaphore.acquire();
                System.out.println("Acquired one semaphore");
                Thread.sleep(2000);
                semaphore.release();
                System.out.println("Released one semaphore");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IntStream.range(0, 20).forEach(i -> executorService.execute(command));
    }
}
