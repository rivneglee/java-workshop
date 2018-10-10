package com.stardust.concurrency.java5;

import java.util.concurrent.*;

public class _SynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue();

        Runnable producer = () -> {
            System.out.println("Trying to put something into queue");
            System.out.println(queue.offer("Q"));
            System.out.println("Things put finished");
            System.out.println("Trying to put something into queue");
            System.out.println(queue.offer("Q"));
            System.out.println("Things put finished");

        };

        Runnable consumer = () -> {
            try {
                Thread.sleep(3000);
                System.out.println("Trying to get something from queue");
                queue.poll();
                System.out.println("Things fetched");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(producer);
        executorService.execute(consumer);
    }
}
