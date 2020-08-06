package dao;

import bean.Student;

import java.sql.*;

public class JDBCUtil {

    private final static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=Asia/Shanghai";
    private static Connection conn = null;
    private static String sql = null;

    static {
        try {
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL, "root", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static int addStu(Student student) {
        sql = "insert into student(id, name, age) values (? , ?, ?);";
        PreparedStatement ps = null;
        int row = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            row = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            close(conn, ps, null);
        }
        return row;
    }

    public static int deleteStu(int id) {
        sql = "delete from student where id = ?;";
        PreparedStatement ps = null;
        int row = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            row = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            close(conn, ps, null);
        }
        return row;
    }

    

    public static void close(Connection conn, Statement statement, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
