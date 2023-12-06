package com.jspservlet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // get请求的处理都跳到post请求
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");

//        response.sendRedirect("register.jsp");
        // 属性usersList的值为users
        //request.setAttribute("usersList", users);

        // 将当前的 request 和 response 对象传递给名为 "selectAll.jsp" 的JSP页面。
        // 这会导致控制权从当前Servlet转移到 "selectAll.jsp" 页面，
        // 且 "selectAll.jsp" 可以处理该请求并生成响应
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
