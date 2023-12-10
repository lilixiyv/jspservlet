package com.jspservlet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // get请求的处理都跳到post请求
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        HttpSession session = request.getSession();
        if( session == null){
            request.setAttribute("errorMessage", "请登录！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession tmp_session = request.getSession();
            if (tmp_session == null || tmp_session.getAttribute("session_identity") == null) {
                request.setAttribute("errorMessage", "请登录！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                if (request.getParameter("book_name") == null){
                    //查询所有结果


                } else {
                    //根据传入参数查询相应结果

                }
                request.getRequestDispatcher("home.jsp").forward(request,response);


            }



        }



    }

}
