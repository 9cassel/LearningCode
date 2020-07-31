package com.qifan.javase.test;

import java.io.File;

public class ListFiles {
    public static void main(String[] args) throws Exception{
        File file = new File("d:/");
        print(file);
    }
    //列出目录结构
    public static void print(File file) {
        if (file.isDirectory()) {
            File result[] = file.listFiles();
            if (result != null) {
                for (int x = 0; x < result.length; x++) {
                    print(result[x]);
                }
            }
        }
        System.out.println(file);
    }
}
