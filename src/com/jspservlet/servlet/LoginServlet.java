package com.jspservlet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
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

        String account = request.getParameter("lg_account");
        String password = request.getParameter("lg_password");
        int error = 2;

        /*
        登录
        数据库存储password的哈希值
        故先对password求哈希，然后在数据库中查询该用户信息
        其中account可能是username/email/phone_number中的任一种
        若account不存在，则将error置为1
        若password错误，则将error置为2
         */

        if (error == 1) {
            request.setAttribute("errorMessage", "用户不存在！");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (error == 2) {
            request.setAttribute("errorMessage", "密码错误！");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("user.jsp").forward(request, response);
        }

    }
}
