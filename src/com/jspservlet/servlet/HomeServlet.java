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
                    String type;
                    int rise;
                    int i;

                    String[] str_split;

                    str_split = time.split(" ", 2);
                    i = 0;
                    // foreach遍历前面的变量必须在foreach中声明
                    for (String substr : str_split) {
                        publish_time[i] = Integer.parseInt(substr);
                        i ++;
                    }

                    str_split = pos_rate.split(" ", 2);
                    i = 0;
                    for (String substr : str_split) {
                        rate[i] = Double.parseDouble(substr);
                        i++;
                    }

                    str_split = price.split(" ", 2);
                    i = 0;
                    for (String substr : str_split) {
                        double_price[i] = Double.parseDouble(substr);
                        i++;
                    }

                    str_split = comment_num.split(" ", 2);
                    i = 0;
                    for (String substr : str_split) {
                        integer_comment[i] = Integer.parseInt(substr);
                        i++;
                    }

                    str_split = sort_order.split(" ", 2);
                    type = str_split[0];
                    rise = Integer.parseInt(str_split[1]);

                    bookList = bookControl.inquire(book_name, publish_time, author, press_name, category, rate, integer_comment, double_price, type, rise);
                }
                request.setAttribute("bookList", bookList);
                request.getRequestDispatcher("home.jsp").forward(request,response);
            } else {
                request.setAttribute("errorMessage", "登录异常！");
                response.sendRedirect("login.jsp");
            }

        }
    }
}
