package com.jspservlet.servlet;

import com.jspservlet.dao.BookControl;
import com.jspservlet.entity.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        if( session == null || session.getAttribute("session_identity") == null) {
            request.setAttribute("errorMessage", "请登录！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            int identity = Integer.parseInt(session.getAttribute("session_identity").toString());
            if (identity == 0){
                BookControl bookControl = new BookControl();
                List<Book> bookList;

                if (request.getParameter("book_name") == null){
                    //查询所有结果
                    bookList = bookControl.selectAll();


                } else {
                    //根据传入参数查询相应结果
                    String book_name = request.getParameter("book_name");
                    String time = request.getParameter("time");
                    String author = request.getParameter("author");
                    String press_name = request.getParameter("press_name");
                    String category = request.getParameter("category");
                    String pos_rate = request.getParameter("pos_rate");
                    String comment_num = request.getParameter("comment_num");
                    String price = request.getParameter("price");
                    String sort_order = request.getParameter("sort_order");
                    Integer[] publish_time = new Integer[2];
                    Double[] rate = new Double[2];
                    Double[] double_price = new Double[2];
                    Integer[] integer_comment = new Integer[2];
                    String type = null;
                    Integer rise = null;
                    switch (time){
                        case "-1":
                            publish_time[0] = 0;
                            publish_time[1] = 3000;
                            break;
                        case "0":
                            publish_time[0] = 0;
                            publish_time[1] = 2010;
                            break;
                        case "1":
                            publish_time[0] = 2010;
                            publish_time[1] = 2015;
                            break;
                        case "2":
                            publish_time[0] = 2015;
                            publish_time[1] = 2020;
                            break;
                        case "3":
                            publish_time[0] = 2020;
                            publish_time[1] = 3000;
                            break;

                    }
                    switch (pos_rate){
                        case "-1":
                            rate[0] = -1.0;
                            rate[1] = 2.0;
                            break;
                        case "0":
                            rate[0] = -1.0;
                            rate[1] = 0.2;
                            break;
                        case "1":
                            rate[0] = 0.2;
                            rate[1] = 0.5;
                            break;
                        case "2":
                            rate[0] = 0.5;
                            rate[1] = 0.8;
                            break;
                        case "3":
                            rate[0] = 0.8;
                            rate[1] = 0.9;
                            break;
                        case "4":
                            rate[0] = 0.9;
                            rate[1] = 2.0;
                            break;

                    }
                    switch (price){
                        case "-1":
                            double_price[0] = -1.0;
                            double_price[1] = 10000.0;
                            break;
                        case "0":
                            double_price[0] = -1.0;
                            double_price[1] = 20.0;
                            break;
                        case "1":
                            double_price[0] = 20.0;
                            double_price[1] = 50.0;
                            break;
                        case "2":
                            double_price[0] = 50.0;
                            double_price[1] = 100.0;
                            break;
                        case "3":
                            double_price[0] = 100.0;
                            double_price[1] = 10000.0;
                            break;
                    }
                    switch (comment_num) {
                        case "-1":
                            integer_comment[0] = -1;
                            integer_comment[1] = 1000;
                            break;
                        case "0":
                            integer_comment[0] = -1;
                            integer_comment[1] = 5;
                            break;
                        case "1":
                            integer_comment[0] = 5;
                            integer_comment[1] = 10;
                            break;
                        case "2":
                            integer_comment[0] = 10;
                            integer_comment[1] = 15;
                            break;
                        case "3":
                            integer_comment[0] = 15;
                            integer_comment[1] = 20;
                            break;
                        case "4":
                            integer_comment[0] = 20;
                            integer_comment[1] = 1000;
                            break;

                    }

                    switch (sort_order){
                        case "0":
                            type = "price";
                            rise = 0;
                            break;
                        case "1":
                            type = "price";
                            rise = 1;
                            break;
                        case "2":
                            type = "pos_rate";
                            rise = 0;
                            break;
                        case "3":
                            type = "pos_rate";
                            rise = 1;
                            break;
                    }

                    bookList = bookControl.inquire(book_name, publish_time, author, press_name, category, rate, integer_comment, double_price, type, rise);
                }
                request.setAttribute("bookList", bookList);
                request.getRequestDispatcher("home.jsp").forward(request,response);
            } else {
                response.sendRedirect("CustomersServlet");
            }

        }
    }
}
