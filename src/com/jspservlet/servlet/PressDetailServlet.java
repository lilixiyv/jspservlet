package com.jspservlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PressDetailServlet")
public class PressDetailServlet extends HomeServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("press_name");
        if(isbn == null){
            request.setAttribute("errorMessage", "缺少查询信息！");
            request.getRequestDispatcher("press_detail.jsp").forward(request,response);
        } else {

        }
    }
}