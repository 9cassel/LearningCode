package com.qifan.javase.test;

import java.io.File;
import java.io.IOException;

public class MDBTest {

    public static void main(String[] args) throws IOException {
        String dataPath = "d:/test/myDatabase.txt";


        MyDataBase mdb = new MyDataBase(dataPath);

        Student stu1 = (new Student(100, "eee", 15, false));
        Student stu = (new Student(14, "ddd", 14, false));
//        mdb.insert(new Student(11, "aaa", 11, true));
//        mdb.insert(new Student(12, "bbb", 12, false));
//        mdb.insert(new Student(13, "ccc", 13, true));
//        mdb.insert(new Student(14, "ddd", 14, false));
//        mdb.insert(new Student(15, "eee", 15, false));
//        mdb.insert(new Student(16, "fff", 16, true));
//        mdb.insert(new Student(17, "hhh", 17, false));
//        mdb.insert(new Student(18, "iii", 18, false));
//        mdb.insert(new Student(19, "jjj", 19, true));
//        mdb.delete(stu1);
        mdb.select();

//        mdb.submit();
        mdb.update(stu1,stu);


//
        System.out.println("=====================");

        mdb.select();

//        mdb.submit();

    }


}
