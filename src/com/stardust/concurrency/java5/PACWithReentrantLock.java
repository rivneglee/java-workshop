package com.stardust.concurrency.java5;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

public class PACWithReentrantLock {
    private static ReentrantLock lock = new ReentrantLock();

    private static Stack<Object> products = new Stack();

    private static final int MAX_QUEUE_SIZE = 5;

    public static void main(String[] args) {
        Thread producerWorker = new Thread(() -> {
            Producer producer = new Producer();
            try {
                while (true) {
                    producer.produce();
                }
            } catch (InterruptedException e) {
            }
        });

        class ConsumerWorker implements Runnable {
            @Override
            public void run() {
                Consumer consumer = new Consumer();
                while (true) {
                    consumer.consume();
                }
            }
        }

        Thread consumerWorker1 = new Thread(new ConsumerWorker());
        Thread consumerWorker2 = new Thread(new ConsumerWorker());
        producerWorker.start();
        consumerWorker1.start();
        consumerWorker2.start();
    }

    private static class Producer {
        public void produce() throws InterruptedException {
            try {
                lock.lock();
                System.out.println("[Producer]Get lock");
                if (products.size() != MAX_QUEUE_SIZE) {
                    System.out.println("[Producer]Queue size is " + products.size() + ", producing new product");
                    products.push(new Object());
                    Thread.sleep(1000);
                }
            } finally {
                System.out.println("[Producer]Release lock");
                lock.unlock();
            }
        }
    }

    private static class Consumer {
        public void consume() {
            try {
                System.out.println("[Consumer]Consuming");
                lock.lock();
                System.out.println("[Consumer]Get lock");
                if (!products.isEmpty()) {
                    System.out.println("[Consumer]Queue size is " + products.size() + ", producing new product");
                    products.pop();
                }
            } finally {
                System.out.println("[Consumer]Release lock");
                lock.unlock();
            }
        }
    }
}
