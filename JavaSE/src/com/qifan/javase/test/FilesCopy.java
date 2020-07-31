package com.qifan.javase.test;

import com.sun.scenario.animation.shared.ClipEnvelope;

import java.io.*;

public class FilesCopy {

    private static void filesCopy(File file, String path) throws IOException {

        File temp = new File(path,file.getName());
        if (file.isFile()) {
            if (!temp.exists()) {
                temp.createNewFile();
            }
            FileInputStream fr = new FileInputStream(file);
            FileOutputStream fw = new FileOutputStream(temp);
            byte[] cs = new byte[1024];
            int len;
            while ((len =fr.read(cs)) != -1) {
                fw.write(cs,0, len);
            }
            fr.close();
            fw.close();
            return;
        }
        if (file.isDirectory()) {
            temp.mkdirs();
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    filesCopy(files[i],temp.getPath());
                }
            }
        }
        return;
    }
}

