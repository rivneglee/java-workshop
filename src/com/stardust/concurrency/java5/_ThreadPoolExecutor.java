package com.stardust.concurrency.java5;


import java.util.concurrent.*;
import java.util.stream.IntStream;

public class _ThreadPoolExecutor {
    public static class BlockingQueueWrapper extends LinkedBlockingQueue<Runnable> {
        public BlockingQueueWrapper() {
            super(5);
        }

        @Override
        public boolean offer(Runnable runnable) {
            boolean available = super.offer(runnable);
            if (available) System.out.println("Push into queue");
            return available;
        }

        @Override
        public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
            Runnable command = super.poll(timeout, unit);
            if (command != null) System.out.println("Take from queue");
            return command;
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new BlockingQueueWrapper();

        ExecutorService executor
                = new ThreadPoolExecutor(5, 20, 20l, TimeUnit.SECONDS, queue);

        IntStream.range(0, 20).forEach(i -> {
            try {
                Thread.sleep(100);
                Runnable runnable = () -> {
                    int x = 0;
                    System.out.println("THREAD INDEX:" + i);
                    System.out.println("QUEUE SIZE:" + queue.size());
                    while(x < 10) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        x++;
                    }
                };
                executor.execute(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
