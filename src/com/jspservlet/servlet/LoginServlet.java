package com.jspservlet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

        String account = request.getParameter("lg_account");
        String password = request.getParameter("lg_password");
        int error = 0;

        /*
        登录
        数据库存储password的哈希值
        故先对password求哈希，然后在数据库中查询该用户信息
        其中account可能是username/email/phone_number中的任一种
        若account不存在，则将error置为1
        若password错误，则将error置为2
        若存在，则返回相关信息，可以为JAVA类
         */

        if (error == 1) {
            request.setAttribute("errorMessage", "用户不存在！");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (error == 2) {
            request.setAttribute("errorMessage", "密码错误！");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            //使用Cookie来防止未登录就直接访问用户界面的情况
            Cookie cookie = new Cookie("account",account);
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
            request.getRequestDispatcher("user.jsp").forward(request, response);
        }

    }
}
