package com.test.nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Scanner;

/**
 * @Author: movie
 * @Date: 2020/11/12 14:55
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        var path = "C:\\Users\\23141\\Desktop\\javaguide.txt";
        File file = new File(path);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        MappedByteBuffer mappedByteBuffer = new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY, 0, length);
        for (int i = 0; i < length; i++) {
            byte b = mappedByteBuffer.get();
            bytes[i] = b;
        }
        Scanner scanner = new Scanner(new ByteArrayInputStream(bytes)).useDelimiter(" ");
        while (scanner.hasNext()) {
            System.out.print(scanner.next());
        }
        System.out.println("-----");
        catFile(Channels.newChannel(System.out), path);
    }

    public static void catFile(WritableByteChannel target, String file) {
        FileChannel channel = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            channel = fileInputStream.getChannel();
            channel.transferTo(0, channel.size(), target);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
