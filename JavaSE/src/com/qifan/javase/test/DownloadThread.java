package com.qifan.javase.test;

import com.qifan.javase.test.FileDownload.FileBlock;

import java.io.*;
import java.util.Queue;

public class DownloadThread extends Thread {

    private FileDownload fileDownload;
    private File tempFile;
    private FileBlock fileBlock;
    private FileOutputStream fos = new FileOutputStream(tempFile);
    private ObjectOutputStream oos = new ObjectOutputStream(fos);

    public DownloadThread(File tempFile, FileBlock fileBlock, FileDownload fileDownload) throws IOException {
        this.tempFile = tempFile;
        this.fileBlock = fileBlock;
        this.fileDownload = fileDownload;
    }

    @Override
    public void run() {

        int len = 0;
        byte[] buff = new byte[1024];
        try {
            oos.writeObject(fileBlock);
            fileDownload.addDF(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
            fileDownload.addFB(fileBlock);
        }
    }
}
