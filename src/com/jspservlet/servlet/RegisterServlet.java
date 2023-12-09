package com.jspservlet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // get请求的处理都跳到post请求
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String account = request.getParameter("rg_account");
        String username = request.getParameter("rg_username");
        String password = request.getParameter("rg_password");
        String email = request.getParameter("rg_email");
        String phone_number = request.getParameter("rg_phone_number");
        int error = 0;

        if (username == null){
            response.sendRedirect("login.jsp");
        }
        else {

            /*
            注册
            需实现：
            1. 查询账号信息，若已存在，则将error置为1
            2. 若不存在，则对密码求sha256，然后将账号信息插入数据库
            */



            if (error == 1) {
                request.setAttribute("errorMessage", "用户已存在！");
                request.getRequestDispatcher("register.jsp").forward(request, response);


            } else {
                request.setAttribute("successMessage", "注册成功！");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }


        }

        // 属性usersList的值为users
        //request.setAttribute("usersList", users);

    }
}
