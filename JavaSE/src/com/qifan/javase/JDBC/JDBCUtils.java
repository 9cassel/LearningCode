package com.qifan.javase.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {

    private final static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=Asia/Shanghai";
    private static Connection conn = null;
    private static boolean driverFlag = true;
    private static String sql = null;
    private static Statement statement = null;

    static {
        try {
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,"root","123456");
            statement = conn.createStatement();
        } catch (Exception e) {
            driverFlag = false;
            System.out.println("Failed to load driver");
//            e.printStackTrace();
        }
    }

    public static int addStu(Student student) {
        String name = student.getName();
        int age = student.getAge();
        int row = 0;
        sql = "insert into student(name,age) values ('"+ name +"', "+ age +" );";
        try {
            row = statement.executeUpdate(sql);
        } catch (Exception e) {
        } finally {
            try {
//                statement.close();
//                conn.close();
            } catch(Exception e) {
            }
        }
        return row;
    }

    public static int updateStu(int id, Student student) {
        String name = student.getName();
        int age = student.getAge();
        int row = 0;
        sql = "update student set name = '"+ name +"' , age = "+ age +" where id = "+ id +" ";
        try {
            row = statement.executeUpdate(sql);
        } catch (SQLException throwables) {

        } finally {
            try {
                statement.close();
                conn.close();
            } catch (Exception e) {

            }
        }
        return row;
    }

    public static int deleteStu(int id) {
        sql = "delete from student where id =" + id;
        int row = 0;
        try {
            row = statement.executeUpdate(sql);
        } catch (Exception e) {

        } finally {
            try {
                statement.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return row;
    }

    // public static List<Student> selectAll(int pageSize, int pageCount) {
    //     sql = "select id,name,age from student limit " + (pageCount - 1)*pageSize +
    //             "," + pageCount*pageSize;
    //     System.out.println(sql);
    //     List<Student> list = new ArrayList();
    //     ResultSet rs = null;
    //     try {
    //         rs = statement.executeQuery(sql);
    //         while (rs.next()) {
    //             int id = rs.getInt(1);
    //             String name = rs.getString(2);
    //             int age = rs.getInt(3);
    //             Student stu = new Student(id,name,age);
    //             list.add(stu);
    //         }
    //     } catch (Exception e) {
    //     } finally {
    //         try {
    //             statement.close();
    //             conn.close();
    //         } catch(Exception e){
    //         }
    //     }
    //     return list;
    // }





}
