package com.jspservlet.servlet;

import com.jspservlet.dao.CustomerDao;
import com.jspservlet.entity.Customer;
import com.jspservlet.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    @Override
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
            int identity = Integer.parseInt(session.getAttribute("session_identity").toString());

                String account = session.getAttribute("session_account").toString();
                try {
                    Customer customer = new CustomerDao().GetAccountInfo(account);
                    request.setAttribute("account", customer);
                    if (identity == 0){
                        request.getRequestDispatcher("account.jsp").forward(request,response);
                    } else {
                        request.getRequestDispatcher("admin_account.jsp").forward(request,response);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }



        }
    }
}
