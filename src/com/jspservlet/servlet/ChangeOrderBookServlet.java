package com.jspservlet.servlet;

import com.jspservlet.dao.OrderControl;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ChangeOrderBookServlet")
public class ChangeOrderBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String isbn = request.getParameter("isbn");
        String str_quantity = request.getParameter("order_sum");
        if (isbn == null || str_quantity == null || isbn.isEmpty() || str_quantity.isEmpty()) {
            request.setAttribute("errorMessage", "信息有误!");
            request.getRequestDispatcher("book_detail.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            if (session == null || session.getAttribute("session_identity") == null) {
                request.setAttribute("errorMessage", "请登录！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {

                String account = session.getAttribute("session_account").toString();
                int quantity = Integer.parseInt(str_quantity);
                if (quantity <= 0) {
                    request.setAttribute("errorMessage", "购买数量有误！");
                    request.getRequestDispatcher("book_detail.jsp").forward(request, response);
                } else {
                    new OrderControl().changeOrderBook(account, isbn, quantity);
                    request.getRequestDispatcher("CurrentOrderServlet").forward(request, response);
                }

            }
        }
    }
}
