package com.jspservlet.dao;

import com.jspservlet.entity.*;
import com.jspservlet.util.dbConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderControl {

    public void payOrder(String customerId) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        try {
            Customer customer = new Customer();
            ps = conn.prepareStatement("select orders.* from orders,customer " +
                    "where orders.customer_id=customer.customer_id and customer.customer_id=?");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                customer.setID(customerId);
                List<Order> orderList = new ArrayList<>();
                Order order = new Order(rs.getString(1), customer
                        , rs.getString(2), rs.getString(3),
                        rs.getDouble(5), 0, new ArrayList<>());
                orderList.add(order);
                customer.setOrders(orderList);
            }
            ps = conn.prepareStatement("select level,purchase_sum from customer " +
                    "where customer_id=?;");
            rs = ps.executeQuery();
            customer.setTotalCost(rs.getDouble(2));
            customer.setVipLevel(rs.getInt(1));
            customer.upgradeVip();
            ps = conn.prepareStatement("update UPDATE customer SET level=?,purchase_sum=? " +
                    "where customer_id=?;");
            ps.setDouble(1, customer.getVipLevel());
            ps.setDouble(2, customer.getTotalCost());
            ps.setString(3 ,customerId);
            ps.executeUpdate();
            ps = conn.prepareStatement("INSERT INTO orders update_time,receipt_place," +
                    "customer_id,price_sum values(CURRENT_TIME(),?,?,?)");

            ps.setString(1, customer.getLocation());
            ps.setString(2, customerId);
            ps.setDouble(3, 0.0);
            ps.executeUpdate();
//            ps = conn.prepareStatement("INSERT INTO orders order_id,update_time,receipt_place," +
//                    "customer_id,price_sum values(?,CURRENT_TIME(),?,?,?)");
//            ps.setInt(1, customer.getOrders().size() + 1);
//            ps.setString(2, customer.getLocation());
//            ps.setString(3, customerId);
//            ps.setDouble(4, 0.0);
//            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> checkOrder(String customerId) {
        List<Book> bookList = new ArrayList<>();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        try {
//            ps = conn.prepareStatement("select book_name, price " +
//                    "from orders natural join order_book natural join" +
//                    "book where customer_id = ?;");
            ps = conn.prepareStatement("select book.*,orders.* " +
                    "from book natural join order_book natural join orders " +
                    "natural join customer where customer.current_order_id = order_id and customer.customer_id = ?;");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString(1));
                book.setPrice(rs.getDouble(2));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void addOrderBook(String customerId, String isbn, int quantity) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("select orders.* " +
                    "from orders natural join customer " +
                    "where customer.current_order_id = orders.order_id and customer_id = ?");// 查询当前订单
            ps.setString(1, customerId);
            rs = ps.executeQuery();

            if (rs.next()){
                int orderId = rs.getInt(1);

                ps = conn.prepareStatement("select price from book where ISBN=?;");
                ps.setString(1, isbn);
                rs = ps.executeQuery();

                // TODO orderSum，此处代码逻辑为一次只能买一本
                if (rs.next()) {
                    int price = rs.getInt(1);
                    ps = conn.prepareStatement("select * from order_book where ISBN = ? and order_id = ?");//查询order_book
                    ps.setString(1, isbn);
                    ps.setInt(2, orderId);
                    rs = ps.executeQuery();
                    if (rs.next()) {// 若已存在，则更新
                        int orderSum = rs.getInt(3);
                        orderSum += quantity;
                        ps = conn.prepareStatement("UPDATE order_book SET order_sum=? where ISBN = ? and order_id=?");
                        ps.setInt(1, orderSum);
                        ps.setString(2, isbn);
                        ps.setInt(3, orderId);
                        ps.executeUpdate();
                    } else {// 若不存在，则插入
                        ps = conn.prepareStatement("INSERT INTO order_book values(?,?,?);");
                        ps.setString(1, isbn);
                        ps.setInt(2, orderId);
                        ps.setInt(3, quantity);
                        ps.executeUpdate();
                    }
                    ps = conn.prepareStatement("select orders.price_sum,customer.level from " +
                            "orders natural join customer where order_id = ?");
                    ps.setInt(1, orderId);
                    rs = ps.executeQuery();
                    if (rs.next()){
                        double finalMoney = price * quantity * (1-0.1* (double) rs.getInt(2))
                                + rs.getDouble(1);
                        ps = conn.prepareStatement("UPDATE orders SET update_time=CURRENT_TIME(),price_sum=? " +
                                "where order_id=?");
                        ps.setDouble(1, finalMoney);
                        ps.setInt(2, orderId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderBook(String customerId, String isbn) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("select order_sum,order_id from order_book where ISBN=?;");
            ps.setString(1, isbn);
            rs = ps.executeQuery();
            String orderId = rs.getString(2);
            int orderSum = rs.getInt(1);
            orderSum -= 1;
            if (0 == orderSum) {
                ps = conn.prepareStatement("DELETE from order_book where ISBN=?;");
                ps.setString(1, isbn);
                ps.executeQuery();
            } else {
                ps = conn.prepareStatement("UPDATE order_book SET order_sum=? where order_id=?");
                ps.setInt(1, orderSum);
                ps.setString(2, orderId);
                ps.executeQuery();
            }

            ps = conn.prepareStatement("select price from book where ISBN=?;");
            ps.setString(1, isbn);
            rs = ps.executeQuery();
            double price = rs.getDouble(1);

            ps = conn.prepareStatement("select orders.price_sum,customer.current_order_id " +
                    "from orders,customer where customer.customer_id=? and " +
                    "customer.current_order_id = orders.order_id;");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            double finalMoney = rs.getDouble(1) - price;
            ps = conn.prepareStatement("UPDATE orders SET update_time=CURRENT_TIME(),price_sum=? " +
                    "where order_id=?");
            ps.setDouble(1, finalMoney);
            ps.setString(2, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getOrderByCustomer(String customerId) {
        Order order = new Order();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("select orders.* from orders,customer " +
                    "where customer.customer_id = ? and " +
                    "customer.current_order_id = orders.order_id;");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            Customer customer = new Customer();
            customer.setID(rs.getString(4));
            order = new Order(rs.getString(1),
                    customer,
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(5), 0,
                    new ArrayList<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
