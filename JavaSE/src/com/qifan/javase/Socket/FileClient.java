package com.qifan.javase.Socket;

import java.io.*;
import java.net.Socket;

public class FileClient {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        try {

            Socket fileClient = new Socket("127.0.0.1",19999);


                InputStream is = fileClient.getInputStream();
                OutputStream os = fileClient.getOutputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ObjectOutputStream oos = new ObjectOutputStream(os);

            while (true) {

                System.out.println("请输入命令：");
                FileCommand cmd = command();
                if (cmd == null) {
                    continue;
                }
                if ("exit".equalsIgnoreCase(cmd.getCommandType())) {
                    break;
                }
                oos.writeObject(cmd);
                FileResult result = (FileResult) ois.readObject();
                String state = read(result);
                state = state == null ? "" : state;
                switch (state) {
                    case "upload":
                        cmd = argument();
                        if (cmd == null) {
                            System.out.println("取消上传");
                        } else if (cmd.getFile().isFile()) {
                            oos.writeObject(cmd);
                            upload(cmd.getFile(), os);
                        } else {
                            System.out.println("上传失败，无法上传文件夹！");
                        }
                        break;
                    case "download":
                        File file = (File) result.getResult();
                        cmd = argument();
                        if (cmd == null) {
                            System.out.println("取消下载");
                        } else if (cmd.getFile().isDirectory()) {
                            oos.writeObject(cmd);
                            download(file, cmd.getPath(), is);
                        } else {
                            System.out.println("下载失败，保存路径有误！");
                        }
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    }

    private static void download(File src, String dest, InputStream is) throws IOException {

        String destPath = (String) dest + "/";
        File file = new File(destPath + src.getName());
        long length = (int) src.length();
        while (file.exists()) {
            String name = file.getName();
            int i = name.lastIndexOf(".");
            String prefix = name.substring(0,i);
            String suffix = name.substring(i);
            file = new File(destPath + prefix + "(1)" + suffix);
        }
        file.createNewFile();

        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bytes = new byte[1024 * 1024];
        int len = -1;
        long temp = 0;
        while (true) {
            len = bis.read(bytes);
            temp += len;
            bos.write(bytes, 0, len);
            if (temp >= length) {
                break;
            }
        }
        bos.flush();
        System.out.println("下载成功！");
        bos.close();
    }

    private static void upload(File file, OutputStream os) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] bytes = new byte[1024 * 1024];
        int len = -1;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.flush();
        bis.close();
        System.out.println("上传成功！");
    }

    private static FileCommand argument() throws IOException {
        while (true) {
            String cmd = br.readLine().trim();
            if ("-1".equals(cmd)) {
                return null;
            }
            if (cmd == null || "".equals(cmd)) {
                System.out.println("命令输入错误，请重试！");
                continue;
            }
            File file = new File(cmd);
            if (file.exists()) {
                return new FileCommand(cmd);
            } else {
                System.out.println("文件路径有误，请重新输入！");
            }
        }
    }

    private static FileCommand command() {

        try {

            String cmd = br.readLine().trim();
            int index = cmd.indexOf(" ");
            if ("exit".equalsIgnoreCase(cmd)) {
                return new FileCommand("exit","");
            }
            if ("ls".equalsIgnoreCase(cmd)) {
                return new FileCommand("ls", "");
            }
            if ((cmd == null || "".equals(cmd) || index == -1)) {
                System.out.println("命令输入错误，请重试！");
                return null;
            }
            String commandType = cmd.substring(0,index).toLowerCase();
            String argument = cmd.substring(index + 1);
            return new FileCommand(commandType,argument);

        } catch (IOException e) {
            System.out.println("输入出错，请重新输入");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    private static String read(FileResult result) {

        String state = null;

        if (0 != result.getCode()) {
            System.out.println(result.getCode() + ":" +result.getMessage());
            return null;
        }

        switch (state = result.getResultType()) {
            case "ls":
                File[] files = (File[]) result.getResult();
                for (File file : files) {
                    System.out.println(file.getName());
                }
                break;
            case "cd":
                System.out.println("目录更换成功！");
                break;
            case "upload":
                System.out.println("请输入上传的文件路径：");
                break;
            case "download":
                System.out.println("请输入保存的文件路径：");
                break;
        }
        return state;
    }
}
