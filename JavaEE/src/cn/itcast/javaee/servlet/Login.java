package cn.itcast.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<h1>login...</h1>");
        Enumeration<String> paraNames = request.getParameterNames();
        while (paraNames.hasMoreElements()) {
            String paraName = paraNames.nextElement();
            String paraValue = request.getParameter(paraName);
            System.out.println(paraName + ":" + paraValue);
        }
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if ("admin".equals(name) && "123".equals(password)) {
            response.getWriter().println("<h2> Success! </h2>");
        } else {
            response.getWriter().println("<h2> Fail! </h2>");
        }
    }
}
