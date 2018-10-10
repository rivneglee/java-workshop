package com.stardust.concurrency.basic;

public class ThreadSafeSingleton {
    static class StaticClassSingleton {
        private StaticClassSingleton() {}

        private static class SingletonHandler {
            static StaticClassSingleton instance = new StaticClassSingleton();
        }

        public static StaticClassSingleton getInstance() throws InterruptedException {
            return SingletonHandler.instance;
        }
    }

    static class DCLSingleton {
        private DCLSingleton() {}

        volatile private static DCLSingleton instance;

        public static DCLSingleton getInstance() throws InterruptedException {
            if (instance == null) {
                synchronized (instance) {
                    if (instance == null) {
                        instance = new DCLSingleton();
                    }
                }
            }
            return instance;
        }
    }
}
