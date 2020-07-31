package com.qifan.javase.Socket;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

    private DatagramSocket server = null;

    private int port = 18888;

    UDPReceive receive;
    UDPSend send;
    boolean flag = true;

    public UDPServer() throws SocketException {
        server = new DatagramSocket(port);
        receive = new UDPReceive(server);
        receive.start();
    }

    public UDPServer(int port) throws SocketException {
        server = new DatagramSocket(port);
        receive = new UDPReceive(server);
        receive.start();
    }

    public void start() {
        send = new UDPSend(server);
        send.start();
    }


    public void stop() {
        receive.setFlag(false);
    }

    class UDPSend extends Thread {

        DatagramSocket server = null;
        boolean flag = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public UDPSend(DatagramSocket ds) {
            this.server = ds;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        DatagramPacket packet = null;

        @Override
        public void run() {

            String hostName;
            int port = 18888;

            try {
                System.out.println("请输入连接的IP地址：");
                hostName = br.readLine().trim();
                System.out.println("请输入端口号：");
                port = Integer.parseInt(br.readLine().trim());
                System.out.println("请输入您要发送的信息：");
                while (flag) {
                    String message = br.readLine();
                    if ("exit".equalsIgnoreCase(message.trim())) {
                        break;
                    }
                    packet = new DatagramPacket(message.getBytes(),message.length(),InetAddress.getLocalHost(),port);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    class UDPReceive extends Thread{

        DatagramSocket server = null;
        boolean flag = true;

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public UDPReceive(DatagramSocket ds) {
            this.server = ds;
        }

        byte[] buff = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buff, 1024);

        @Override
        public void run() {
            while(flag) {
                try {
                    while(flag) {
                        server.receive(packet);
                        String message = new String(buff, packet.getOffset(), packet.getLength());
                        String from = new String(packet.getAddress().getHostAddress() + ":" + packet.getPort());
                        System.out.println(message + "/t/t/t" + from);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer();
            server.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
