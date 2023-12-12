package com.jspservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AuthorDetailServlet")
public class AuthorDetailServlet extends HomeServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author_name = request.getParameter("author_name");
        if(author_name == null){
            request.setAttribute("errorMessage", "缺少查询信息！");
            request.getRequestDispatcher("author_detail.jsp").forward(request,response);
        } else {

        }
    }
}
