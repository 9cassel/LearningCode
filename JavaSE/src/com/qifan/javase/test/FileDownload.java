package com.qifan.javase.test;

import java.io.*;
import java.util.*;

public class FileDownload {

    private File srcFile;
    private File temp;
    private File destFile;
    private int blockCount = 0;
    private FileInputStream fis;

    public FileDownload(File srcFile, String destPath) throws Exception {
        if (!srcFile.isFile()) {
            throw new Exception("不可下载文件夹");
        }

        this.srcFile = srcFile;
        destFile = new File(destPath);
        if (!destFile.exists()) {

        }
        setTempPath(DEFAULT_PATH);
    }

    class FileBlock implements Serializable {

        public FileBlock(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        private int id;

        List<byte[]> fileBlock = new ArrayList<>();
    }

    private Queue<FileBlock> fileBlocks = new LinkedList<>();
    private Queue<File> downloadFiles = new LinkedList<>();

    public void addFB(FileBlock fileBlock) {
        fileBlocks.add(fileBlock);
    }

    public void addDF(File downloadFile) {
        downloadFiles.add(downloadFile);
    }


    private static final String DEFAULT_PATH = "d:/temp";
    public void setTempPath(String path) {
        temp = new File(path);
        if (!temp.exists()) {
            temp.mkdirs();
        }
    }

    
    
    private void read() throws IOException {

        fis = new FileInputStream(srcFile);
        byte[] bytes = new byte[1024];
        int maxBytes = 1024;
        int len = 0;
        int count = 0;
        FileBlock fb = null;
        while (true) {
            if (count++ % maxBytes == 0 || (len = fis.read(bytes)) == -1) {
                if (fb != null) {
                    fileBlocks.add(fb);
                }
                if (len == -1) {
                    break;
                }
                fb = new FileBlock(blockCount++);
            }
            fb.fileBlock.add(Arrays.copyOf(bytes, len));
        }
        fis.close();
    }



}
