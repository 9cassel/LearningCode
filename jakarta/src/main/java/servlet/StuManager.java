package servlet;

import bean.Paging;
import bean.Student;
import dao.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stu")
public class StuManager extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "selectAll":
                handleGetPage(request, response);
                break;
            case "add":
                addStu(request, response);
                break;
            case "delete":
                handleDeleteStu(request, response);
                break;
            case "update":
                handleUpdateStu(request, response);
                break;
            case "get":
                handleGetStudent(request,response);
                break;
            default:
                break;
        }
    }

    private void handleGetStudent(HttpServletRequest request, HttpServletResponse response) {
        String text = request.getParameter("text");
        if (text == null) {
            text = "";
        }
        request.setAttribute("text",text);
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = StuDao.selectOne(id);
        request.setAttribute("stu",student);
        try {
            request.getRequestDispatcher("editStu.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleGetPage(HttpServletRequest request, HttpServletResponse response) {


        String text = request.getParameter("text");
        if (text == null) {
            text = "";
        }
        request.setAttribute("text",text);
        int currentPage = 1;
        int pageSize = 10;

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        try {
            currentPage = Integer.parseInt(currentPageStr);
        } catch (NumberFormatException e) {
        }
        try {
            pageSize = Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) {
        }
        Paging paging = StuDao.selectSplit(currentPage, pageSize, text);
//        request.setAttribute("paging", paging);

        try {
            request.getRequestDispatcher("stuPage.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdateStu(HttpServletRequest request, HttpServletResponse response) {
        String text = request.getParameter("text");
        if (text == null) {
            text = "";
        }
        try {
            request.setCharacterEncoding("UTF-8");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
            String age = request.getParameter("age");
            Student student = new Student(Integer.parseInt(id), name, Integer.parseInt(age));
            StuDao.updateStu(student);
            response.sendRedirect("stu?method=selectAll&text="+text);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void handleDeleteStu(HttpServletRequest request, HttpServletResponse response) {
        String text = request.getParameter("text");
        if (text == null) {
            text = "";
        }
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            StuDao.deleteStu(id);
            response.sendRedirect("stu?method=selectAll&text="+text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addStu(HttpServletRequest request, HttpServletResponse response) {

    }


}
