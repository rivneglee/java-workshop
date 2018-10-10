package com.stardust.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

public class _SequenceInputStream {
    public static void main(String[] args) throws IOException {
        InputStream inputStream1 = new ByteArrayInputStream("Hello".getBytes());
        InputStream inputStream2 = new ByteArrayInputStream("World".getBytes());
        SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2);
        int data;
        while((data = sequenceInputStream.read()) != -1) {
            System.out.println((char) data);
        }
    }
}
