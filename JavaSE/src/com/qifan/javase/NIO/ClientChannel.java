package com.qifan.javase.NIO;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ClientChannel {
    public static void main(String[] args) throws Exception {

        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false);
        Selector selector = Selector.open();
        client.register(selector, SelectionKey.OP_CONNECT);
        client.connect(new InetSocketAddress(18888));
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key :
                    keys) {
                System.out.println("1");
                SocketChannel sc = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    sc.finishConnect();
                    sc.configureBlocking(false);
                    System.out.println("服务器已连接。。。");
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    try {
                        System.out.println("可读。。。。");
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        sc.read(buff);
                        buff.flip();
                        byte[] bytes = new byte[buff.limit()];
                        buff.get(bytes);
                        System.out.println(new String(bytes));
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_WRITE);
                    } catch (Exception e) {
                        sc.close();
                    }
                } else if (key.isWritable()) {
                    try {
                        System.out.println("可写。。。。。。");
                        sc.write(ByteBuffer.wrap("Hello World".getBytes()));
                        sc.configureBlocking(false);
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
