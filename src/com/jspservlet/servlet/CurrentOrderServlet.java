package com.jspservlet.servlet;

import com.jspservlet.dao.CustomerDao;
import com.jspservlet.dao.OrderControl;
import com.jspservlet.entity.Book;
import com.jspservlet.entity.Order;
import com.jspservlet.entity.OrderBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/CurrentOrderServlet")
public class CurrentOrderServlet extends HttpServlet {
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
            if (identity == 1 ){
                request.setAttribute("errorMessage", "身份异常！");
                response.sendRedirect("login.jsp");
            } else {
                String account = session.getAttribute("session_account").toString();
                Order order = new OrderControl().getOrderByCustomer(account);
                List<OrderBook> orderBookList = new CustomerDao().getCurrentOrder(account);

                int vip_level = new CustomerDao().getVipLevel(account);
                request.setAttribute("current_order",order);
                request.setAttribute("current_order_books", orderBookList);
                request.setAttribute("vip_level", vip_level);
                request.getRequestDispatcher("current_order.jsp").forward(request,response);

            }
        }

    }

}
