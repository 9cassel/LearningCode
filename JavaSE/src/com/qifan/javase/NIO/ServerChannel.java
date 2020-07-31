package com.qifan.javase.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

public class ServerChannel {
    public static void main(String[] args) throws Exception{

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(18888));
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while(true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();

            for (SelectionKey key :
                    keys) {

                System.out.println("1");
                SelectableChannel keyChannal = key.channel();

                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) keyChannal;
                    SocketChannel clientChannel = ssc.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector,SelectionKey.OP_WRITE);
                    System.out.println("可连接。。。。。");
                } else if (key.isReadable()) {
                    System.out.println("可读。。。。。。");
                    SocketChannel sc = (SocketChannel) keyChannal;
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    try {
                        sc.read(buff);
                        buff.flip();
                        byte[] dest = new byte[buff.limit()];
                        buff.get(dest);
                        System.out.println(new String(dest));
                    } catch (Exception e) {
                        sc.close();
                    }
                    sc.register(selector,SelectionKey.OP_WRITE);
                } else if (key.isWritable()) {
                    System.out.println("可写。。。。。。");
                    SocketChannel sc = (SocketChannel) keyChannal;

                    String message = "Hello World";
                    try {
                        sc.write(ByteBuffer.wrap(message.getBytes()));
                        sc.register(selector, SelectionKey.OP_READ);
                    } catch (Exception e) {
                        sc.close();
                    }
                }
                keys.remove(key);
            }

        }

    }
}
