package com.jspservlet.servlet;

import com.jspservlet.dao.BookControl;
import com.jspservlet.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HomeServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        if(isbn == null){
            request.setAttribute("errorMessage", "缺少查询信息！");
            request.getRequestDispatcher("book_detail.jsp").forward(request,response);
        } else {
            Book book = new BookControl().selectByIsbn(isbn);
            if (book == null){
                request.setAttribute("errorMessage", "无法查询到相关信息！");
                request.getRequestDispatcher("book_detail.jsp").forward(request,response);
            } else {
                request.setAttribute("book_detail", book);
                request.getRequestDispatcher("book_detail.jsp").forward(request,response);
            }
        }
    }
}
