package com.jspservlet.servlet;

import com.jspservlet.dao.AdminDao;
import com.jspservlet.entity.Administrator;
import com.jspservlet.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AdminsServlet")
public class AdminsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // get请求的处理都跳到post请求
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        if( session == null || session.getAttribute("session_identity") == null){
            request.setAttribute("errorMessage", "请登录！");
            response.sendRedirect("login.jsp");
        } else {
            int identity = Integer.parseInt(session.getAttribute("session_identity").toString());
            if (identity == 0){
                request.setAttribute("errorMessage", "身份认证失败！");
                response.sendRedirect("login.jsp");
            } else {
                try {
                    List<Administrator> administratorList = new AdminDao().getAdminInformation();
                    request.setAttribute("administratorList", administratorList);
                    request.getRequestDispatcher("admins.jsp").forward(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }
}
