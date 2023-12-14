package com.jspservlet.dao;

import com.jspservlet.entity.Book;
import com.jspservlet.entity.Order;
import com.jspservlet.util.dbConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoughtBookControl {
    public Book notBuyBook(String customerId) {
        Book book = new Book();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select book.ISBN,book.price from book,order_book,orders,customer " +
                    "where customer.customer_id=? and customer.customer_id = orders.customer_id and orders.order_id " +
                    "= order_book.order_id and order_book.ISBN = book.ISBN and orders.order_id != customer.current_order_id;");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            book.setPrice(rs.getDouble(2));
            book.setIsbn(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return book;
    }

    public Order getOrderBook(String customerId) {
        Order order = new Order();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select order_book.* from order_book,customer where" +
                    "customer.customer_id=? and customer.customer_id = orders.customer_id and " +
                    "orders.order_id = order_book.order_id;");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            order.setAmount(rs.getInt(3));
            order.setOrderNumber(rs.getString(2));
            List<Book> bookList = new ArrayList<>();
            Book book = new Book();
            book.setIsbn(rs.getString(1));
            bookList.add(book);
            order.setBookList(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return order;
    }
}
