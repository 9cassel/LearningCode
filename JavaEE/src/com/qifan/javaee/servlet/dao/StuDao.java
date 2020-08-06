package com.qifan.javaee.servlet.dao;

import com.qifan.javaee.servlet.bean.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StuDao {

    private static Connection conn = JDBCUtil.getConn();

    public static ArrayList<Student> selectAll() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from student");
            ArrayList<Student> stuList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String stuName = rs.getString(2);
                int age = rs.getInt(3);
                Student student = new Student(id, stuName, age);
                stuList.add(student);
            }
            return stuList;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
    }
}
