package com.qifan.javase.JDBC;

import java.util.List;
import java.util.Random;

public class JdbcTest {

    public static void main(String[] args) {
        Student stu1 = new Student("张三", 18);
        int i = JDBCUtils.addStu(stu1);
        System.out.println(i);
//        Student stu2 = new Student("李四", 20);
//        int j = JDBCUtils.updateStu(1, stu2);
//        System.out.println(j);
//        int i = JDBCUtils.deleteStu(1);
//        System.out.println(i);
//        for (int i = 0; i < 100; i++) {
//            Student stu = new Student("stu"+i, (int)(Math.random()*10+20));
//            JDBCUtils.addStu(stu);
//        }
//        List<Student> list = JDBCUtils.selectAll(5, 10);
//        for (Student stu :
//                list) {
//            System.out.println(stu);
//        }
    }

}
