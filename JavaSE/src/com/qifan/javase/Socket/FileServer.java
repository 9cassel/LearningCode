package com.qifan.javase.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    private static String ROOT_PATH = null;
    private static File ROOT_File = null;
    private static String currentPath;

    static {
        ROOT_PATH = "D:\\project\\code\\test";
        ROOT_File = new File(ROOT_PATH);
        currentPath = ROOT_PATH;
    }

    public static void main(String[] args) {
        int port = 19999;
        ServerSocket serverSocket;

        try {

            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();


                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                ObjectInputStream ois = new ObjectInputStream(is);

            while (true) {

                FileCommand cmd = (FileCommand) ois.readObject();
                FileResult result = getResult(cmd);
                oos.writeObject(result);
                if (result.getCode() == 0) {
                    switch (result.getResultType()) {
                        case "upload":
                            cmd = (FileCommand) ois.readObject();
                            File src = getFile(cmd);
                            upload(src, (String)result.getResult(),is);
                            break;
                        case "download":
                            cmd = (FileCommand) ois.readObject();
                            String destPath = cmd.getPath();
                            download((File)result.getResult(), destPath, os);
                            break;
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void download(File file, String destPath, OutputStream os) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] bytes = new byte[1024 * 1024];
        int len = -1;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.flush();
        bis.close();
    }

    private static File getFile(FileCommand cmd) throws Exception {
        File file = cmd.getFile();
        if (file == null || file.isDirectory()) {
            throw new Exception("文件类型或路径有误！");
        }
        return file;
    }

    private static void upload(File src, String dest, InputStream is) throws IOException {
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
        bos.close();
    }

    private static FileResult getResult(FileCommand Command) {

        FileCommand fileCommand = Command;

        String commandType = fileCommand.getCommandType();
        String argument = fileCommand.getCommandArgument();

        String resultType = commandType;
        Object result = null;

        switch (commandType) {
            case "ls":
                result = list(argument);
                if (result == null) {
                    return new FileResult(100, "目录不存在");
                }
                break;
            case "cd":
                result = cd(argument);
                if (result == null) {
                    return new FileResult(200, "文件路径不存在");
                }
                break;
            case "upload":
                result = destFile(argument);
                if (result == null) {
                    return new FileResult(300, "目标路径不可用");
                }
                break;
            case "download":
                result = srcFile(argument);
                if (result == null) {
                    return new FileResult(400,"下载文件路径错误");
                }
                break;
            default:
                return new FileResult(500,"命令输入错误");
        }

        return new FileResult(resultType,result);
    }

    private static File srcFile(String argument) {
        String temp = fileExist(argument);
        File file = new File(temp);
        if (file.isFile()) {
            return file;
        } else {
            return null;
        }
    }

    private static String destFile(String argument) {
        String temp = fileExist(argument);
        File dir;
        if (temp == null) {
            temp = currentPath;
            if (argument.startsWith("/")) {
                temp = ROOT_PATH + argument;
            } else {
                temp += "/" + argument;
            }
            dir = new File(temp);
            dir.mkdirs();
        } else {
            dir = new File(temp);
            if (dir.isFile()) {
                return null;
            }
        }
        return temp;

    }

    private static String cd(String argument) {
        String temp = fileExist(argument);
        if (temp == null) {
            return null;
        } else {
            currentPath = temp;
            return temp;
        }
    }

    private static File[] list(String argument) {
        String path = null;
        if ("".equals(argument)) {
            path = currentPath;
        } else {
            path = fileExist(argument);
        }
        if (path == null) {
            return null;
        } else {
            File files = new File(path);
            return files.listFiles();
        }
    }

    private static String fileExist(String argument) {
        String temp;
        temp = currentPath;
        if (argument.startsWith("/")) {
            temp = ROOT_PATH + argument;
        } else {
            temp += "/" + argument;
        }
        File file = new File(temp);
        if (file.exists()) {
            return temp;
        } else {
            return null;
        }
    }


}
