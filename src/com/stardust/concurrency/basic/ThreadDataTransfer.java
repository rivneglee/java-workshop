package com.stardust.concurrency.basic;

import java.io.*;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ThreadDataTransfer {
    public static void main(String[] args) throws IOException {
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();
        inputStream.connect(outputStream);
        Receiver receiver = new Receiver(inputStream);
        Sender sender = new Sender(outputStream);

        Thread receiveWorker = new Thread(() -> receiver.receive());
        Thread sendWorker = new Thread(() -> sender.send());
        sendWorker.start();
        receiveWorker.start();
    }

    static class Receiver {
        private InputStream inputStream;
        public Receiver(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void receive() {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    static class Sender {
        private OutputStream outputStream;

        public Sender(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        public void send() {
            IntStream.range(0, 5).forEach(num -> {
                try {
                    Thread.sleep(1000);
                    outputStream.write(String.format("Message (%s)\n", num + "").getBytes());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
