package com.qifan.javaee.servlet.webpage;

import com.qifan.javaee.servlet.bean.Student;
import com.qifan.javaee.servlet.dao.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/stuinfo")
public class StuInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        StringBuffer sb = new StringBuffer();
        sb.append("<div style=\"width: 100%; text-align: center\">\n" +
                "    <h1>Student Info</h1>\n" +
                "    <a href=\"export\">导出数据</a>\n" +
                "    <a href=\"import.jsp\">导入数据</a>\n" +
                "</div>");
        sb.append("<div style=\"width: 100%;\">\n" +
                "<table style='width: 60%; border: solid 1px; text-align: center;margin: 0 auto;'>\n" +
                "    <th>\n" +
                "        <tr style='background: lightgray'>\n" +
                "            <td>学号</td>\n" +
                "            <td>姓名</td>\n" +
                "            <td>年龄</td>\n" +
                "        </tr>\n" +
                "    </th>\n" +
                "    <tbody>");
        String toFormat = "<tr>\n" +
                "        <td>%d</td>\n" +
                "        <td>%s</td>\n" +
                "        <td>%d</td>\n" +
                "    </tr>";

        ArrayList<Student> students = StuDao.selectAll();
        for (Student student : students) {
            String stu = String.format(toFormat,student.getId(),student.getName(),student.getAge());
            sb.append(stu);
        }
        sb.append("</tbody>\n" +
                "</table>\n" +
                "</div>");
        response.getWriter().write(sb.toString());
    }
}
