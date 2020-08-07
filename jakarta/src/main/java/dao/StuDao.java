package dao;

import bean.Paging;
import bean.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class StuDao {

    private final static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=Asia/Shanghai";
    private static String sql = null;

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, "root", "123456");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static int addStu(Student student) {
        Connection conn = StuDao.getConn();
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
        Connection conn = StuDao.getConn();
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

    public static int updateStu(Student student) {
        Connection conn = StuDao.getConn();
        sql = "update student set name = ?, age = ? where id = ?;";
        PreparedStatement ps = null;
        int row = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setInt(3, student.getId());
            row = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            close(conn, ps, null);
        }
        return row;
    }

    public static Student selectOne(int id) {
        Connection conn = StuDao.getConn();
        sql = "select name, age from student where id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = new Student();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                student.setName(rs.getString(1));
                student.setAge(rs.getInt(2));
                student.setId(id);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally{
            close(conn, ps, rs);
        }
        return student;
    }

    public static ArrayList<Student> selectAll() {
        Connection conn = StuDao.getConn();
        sql = "select id, name, age from student";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Student> students = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                Student student = new Student(id, name, age);
                students.add(student);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables);
        } finally {
            close(conn, ps, rs);
        }
        return students;
    }

    public static Paging selectSplit(int currentPage, int pageSize) {
        Connection conn = StuDao.getConn();
        Paging paging = new Paging();
        ArrayList<Student> datas = new ArrayList<>();
        String sql1 = "select count(1) from student";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            while (rs.next()) {
                int totalCount = rs.getInt(1);
                paging.setTotalCount(totalCount);
                int totalPage = (totalCount + pageSize - 1) / pageSize;
                paging.setTotalPage(totalPage);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }

        int defaultPage = 1;
        if (currentPage <= 0) {
            currentPage = defaultPage;
        } else if (currentPage >= paging.getTotalPage()) {
            currentPage = paging.getTotalPage();
        }
        paging.setCurrentPage(currentPage);
        String sql2 = "select id, name, age from student order by id limit ?, ?;";
        try {
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                datas.add(student);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            close(conn, ps, rs);
        }
        paging.setDatas(datas);
        paging.setPageSize(pageSize);
        return paging;
    }

    public static Paging selectSplit(int currentPage, int pageSize, String para) {
        Connection conn = StuDao.getConn();
        Paging paging = new Paging();
        ArrayList<Student> datas = new ArrayList<>();
        String txt = ("".equals(para) || para == null) ? "%" : "%" + para + "%";
        String sql1 = "select count(1)" +
                "from student " +
                "where id like ? or name like ? or age like ? ";
        String sql2 = "select id, name, age " +
                "from student " +
                "where id like ? or name like ? or age like ? " +
                "order by id  limit ?, ?;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql1);
            ps.setObject(1, txt);
            ps.setObject(2, txt);
            ps.setObject(3, txt);
            rs = ps.executeQuery();
            while (rs.next()) {
                int totalCount = rs.getInt(1);
                paging.setTotalCount(totalCount);
                int totalPage = (totalCount + pageSize - 1) / pageSize;
                paging.setTotalPage(totalPage);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }

        int defaultPage = 1;
        if (currentPage <= 0) {
            currentPage = defaultPage;
        } else if (currentPage >= paging.getTotalPage()) {
            currentPage = paging.getTotalPage();
        }
        paging.setCurrentPage(currentPage);

        try {
            ps = conn.prepareStatement(sql2);
            ps.setObject(1, txt);
            ps.setObject(2, txt);
            ps.setObject(3, txt);
            ps.setInt(4, (currentPage - 1) * pageSize);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                datas.add(student);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            close(conn, ps, rs);
        }
        paging.setDatas(datas);
        paging.setPageSize(pageSize);
        return paging;
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

//    public static void main(String[] args) {
//            for (int i = 0; i < 100; i++) {
//            int id = 100 + i;
//            String name = "stu" + id;
//            int age = 20 + i % 20;
//            Student student = new Student(id, name, age);
//            int row = addStu(student);
//            System.out.println("新增" + row + "条数据");
//        }

//        int row = updateStu(new Student(199, "Jackson", 20));
//        System.out.println("新增" + row + "条数据");

//        Student student = selectOne(199);
//        System.out.println(student);
//        ArrayList<Student> students = selectAll();
//        for (Student student : students) {
//            System.out.println(student);
//        }
//        deleteStu(2);

//        Paging paging = selectSplit(10, 10);
//        System.out.println(paging);
//    }
}
