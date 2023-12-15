package com.jspservlet.servlet;

import com.jspservlet.dao.CustomerDao;
import com.jspservlet.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteSelfServlet")
public class DeleteSelfServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
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
                String account = session.getAttribute("session_account").toString();
                String confirm = request.getParameter("confirm");
                if (!"1".equals(confirm)){
                    request.setAttribute("errorMessage", "参数异常！");
                    response.sendRedirect("AccountServlet");
                } else {
                    new CustomerDao().deleteAccount(account);
                    session.invalidate();
                    response.sendRedirect("login.jsp");
                }



        }
    }
}
