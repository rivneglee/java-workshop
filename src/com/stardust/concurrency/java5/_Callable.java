package com.stardust.concurrency.java5;


import java.util.concurrent.*;

public class _Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> counter = () -> {
            int i = 0;
            while(i < 10) {
                Thread.sleep(100);
                i++;
            }
            return i;
        };

        FutureTask<Integer> future = new FutureTask(counter);

        Thread counterWorker = new Thread(future);
        counterWorker.start();
        System.out.println("Start running");
        System.out.println(future.get());
        System.out.println("Stop running");
    }
}
