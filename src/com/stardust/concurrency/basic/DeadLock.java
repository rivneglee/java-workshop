package com.stardust.concurrency.basic;

public class DeadLock {

    public static void main(String[] args) {
        DeadLockProducer producer = new DeadLockProducer();

        Thread worker1 = new Thread(() -> {
            try {
                producer.doSomething();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread worker2 = new Thread(() -> {
            try {
                producer.doSomeOtherthing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        worker1.start();
        worker2.start();
    }


    static class DeadLockProducer {
        Object resource1 = new Object();
        Object resource2 = new Object();

        public void doSomething() throws InterruptedException {
            synchronized (resource1) {
                System.out.println("Do some thing start");
                // Do something...
                Thread.sleep(100);
                synchronized (resource2) {
                    // Do some other thing
                }
            }
            System.out.println("Do some thing finished");
        }


        public void doSomeOtherthing() throws InterruptedException {
            synchronized (resource2) {
                System.out.println("Do some other thing started");
                // Do something...
                Thread.sleep(100);
                synchronized (resource1) {
                    // Do some other thing
                }
            }
            System.out.println("Do some other thing finished");
        }
    }
}
