package com.jspservlet.servlet;

import com.jspservlet.dao.AdminDao;
import com.jspservlet.dao.CustomerDao;
import com.jspservlet.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();

        String account = request.getParameter("rg_account");
        String username = request.getParameter("rg_username");
        String password = request.getParameter("rg_password");
        String email = request.getParameter("rg_email");
        String phone_number = request.getParameter("rg_phone_number");
        boolean exists;

        if( session == null || session.getAttribute("session_identity") == null){
            request.setAttribute("errorMessage", "请登录！");
            response.sendRedirect("login.jsp");
        } else {
            int identity = Integer.parseInt(session.getAttribute("session_identity").toString());
            if (identity == 0 ){
                request.setAttribute("errorMessage", "身份异常！");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            } else {
                try {
                    try {
                        exists = new AdminDao().addAdmin(username, account, email, phone_number, password, null);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }

                    if (exists) {
                        request.setAttribute("errorMessage", "用户已存在！");
                        request.getRequestDispatcher("add_admin.jsp").forward(request, response);


                    } else {
                        request.setAttribute("successMessage", "添加成功！");
                        request.getRequestDispatcher("AdminsServlet").forward(request, response);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }
}
