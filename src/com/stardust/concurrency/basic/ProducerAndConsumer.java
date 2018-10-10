package com.stardust.concurrency.basic;

import java.util.Stack;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        Thread producerWorker = new Thread(() -> {
            Producer producer = new Producer();
            try {
                while (true) {
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

    private static Stack<Object> products = new Stack();

    private static final int MAX_QUEUE_SIZE = 5;

    private static class Producer {
        public void produce() throws InterruptedException {
            synchronized (products) {
                while (products.size() == MAX_QUEUE_SIZE) {
                    System.out.println("[Producer]Queue size react to max:" + MAX_QUEUE_SIZE);
                    products.wait();
                }
                if (products.size() >= MAX_QUEUE_SIZE) throw new RuntimeException("Queue exploded!");
                System.out.println("[Producer]Queue size is " + products.size() + ", producing new product");
                products.push(new Object());
                products.notify();
            }
        }
    }

    private static class Consumer {
        public void consume() throws InterruptedException {
            synchronized (products) {
                while (products.isEmpty()) {
                    System.out.println("[Consumer] No product!!");
                    products.wait();
                }
                if (products.isEmpty()) throw new RuntimeException("No product available!!");
                System.out.println("[Consumer]Queue size is " + products.size() + ", consuming product");
                products.pop();
                products.notify();
            }
        }
    }
}
