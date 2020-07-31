package com.qifan.javase.NIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        FileChannel channel1 = new FileInputStream("d:/test/FileServer.java").getChannel();
        FileChannel channel2 = new FileOutputStream("d:/test/fileChannel/FileServer.txt").getChannel();
        channel1.transferTo(0,channel1.size(),channel2);
    }
}
