package com.qifan.javaee.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCUtil {
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
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConn() {
        return conn;
    }

}
