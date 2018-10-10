package com.stardust.concurrency.java5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class _Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final Integer[] i = {0};
        Runnable counter = () -> {
            while(i[0] < 10) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i[0]++;
            }
        };

        FutureTask future = new FutureTask(counter, i);
        Thread counterWorker = new Thread(future);
        counterWorker.start();
        System.out.println("Start running");
        System.out.println(((Integer[])future.get())[0]);
        System.out.println("Stop running");
    }
}
