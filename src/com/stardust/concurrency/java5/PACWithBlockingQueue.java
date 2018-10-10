package com.stardust.concurrency.java5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PACWithBlockingQueue {
    static final int MAX_QUEUE_SIZE = 5;

    static BlockingQueue<Object> products = new LinkedBlockingQueue(MAX_QUEUE_SIZE);

    static class Producer {
        public void produce() throws InterruptedException {
            System.out.println("[Producer]Queue size is " + products.size() + ", producing new product");
            products.put(new Object());
        }
    }

    static class Consumer {
        public void consume() throws InterruptedException {
            System.out.println("[Consumer]Queue size is " + products.size());
            products.take();
            System.out.println("[Consumer]Consumed");
        }
    }

    public static void main(String[] args) {
        Thread producerWorker = new Thread(() -> {
            Producer producer = new Producer();
            try {
                while (true) {
                    Thread.sleep(3000);
                    producer.produce();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        class ConsumerWorker implements Runnable {
            @Override
            public void run() {
                Consumer consumer = new Consumer();
                try {
                    while (true) {
                        consumer.consume();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Thread consumerWorker1 = new Thread(new ConsumerWorker());
        Thread consumerWorker2 = new Thread(new ConsumerWorker());
        Thread consumerWorker3 = new Thread(new ConsumerWorker());
        producerWorker.start();
        consumerWorker1.start();
        consumerWorker2.start();
        consumerWorker3.start();
    }
}
