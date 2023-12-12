package com.jspservlet.servlet;

import com.jspservlet.dao.CustomerDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
        String address = request.getParameter("rg_address");
        boolean exists = true;
        if (username == null){
            response.sendRedirect("login.jsp");
        }
        else {
            CustomerDao customerDao = new CustomerDao();
            // TODO 待测试
            try {
                exists = customerDao.register(account, username, email, phone_number, address, password);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if (exists) {
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
