package com.stardust.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

public class _ByteArrayInputStream {
    public static void main(String[] args) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream("Hello World".getBytes())) {
            int data;
            while((data = inputStream.read()) != -1) {
                System.out.println(data);
            }
        }

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream("Hello World".getBytes())) {
            byte[] buffer = new byte[4];
            int length;
            while((length = inputStream.read(buffer)) != -1) {
                System.out.println(new String(Arrays.copyOf(buffer, length)));
            }
        }
    }
}
