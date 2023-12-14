package com.jspservlet.servlet;

import com.jspservlet.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
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
            if (identity == 0 ){
                request.setAttribute("errorMessage", "身份异常！");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            } else {
                String isbn = request.getParameter("isbn");
                String book_name = request.getParameter("book_name");
                String time = request.getParameter("time");
                String book_description = request.getParameter("book_description");
                String press_name = request.getParameter("press_name");
                String author_name = request.getParameter("author_name");
                String category_name = request.getParameter("category_name");
                double price = Double.parseDouble(request.getParameter("price"));
                try {
                    new AdminDao().addBookInfo(isbn, book_name, book_description, time, press_name, author_name, category_name, price);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect("HomeServlet");

            }
        }
    }
}
