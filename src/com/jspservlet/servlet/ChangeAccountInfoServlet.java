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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/ChangeAccountInfoServlet")
public class ChangeAccountInfoServlet extends HttpServlet {

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
                    String change_account = request.getParameter("rg_account");
                    String username = request.getParameter("rg_username");
                    String password = request.getParameter("rg_password");
                    String change_password = request.getParameter("rg_new_password");
                    String email = request.getParameter("rg_email");
                    String phone_number = request.getParameter("rg_phone_number");
                    String address = request.getParameter("rg_address");

                    if (change_account != null && !change_account.isEmpty()){
                        boolean exists;
                        if(identity == 0){
                            exists = new CustomerDao().ChangeAccountInfo(account, change_account, username, email, phone_number, address);
                        } else {
                            exists = new CustomerDao().ChangeAccountInfo(account, change_account, username, email, phone_number);
                        }

                        if (exists) {
                            request.setAttribute("errorMessage", "用户已存在！");
                            request.getRequestDispatcher("change_account_info.jsp").forward(request, response);


                        } else {
                            request.setAttribute("successMessage", "修改成功！");
                            session.setAttribute("session_account", change_account);
                            request.getRequestDispatcher("AccountServlet").forward(request, response);

                        }
                    } else if (password != null && !password.isEmpty()) {
                        boolean success = new CustomerDao().ChangeAccountInfo(account, password, change_password);
                        if (!success) {
                            request.setAttribute("errorMessage", "认证失败！");
                            request.getRequestDispatcher("change_account_password.jsp").forward(request, response);


                        } else {
                            request.setAttribute("successMessage", "修改成功！");
                            request.getRequestDispatcher("AccountServlet").forward(request, response);

                        }
                    }

                } catch (SQLException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }



        }
    }
}
