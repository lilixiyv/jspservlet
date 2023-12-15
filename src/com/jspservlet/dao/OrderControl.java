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

    public void payOrder(String customerId, String address) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select orders.price_sum, def_location, orders.order_id " +
                    "from orders natural join customer " +
                    "where orders.order_id = current_order_id and customer.customer_id=?");// 获取当前订单总金额
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                double order_price_sum = rs.getDouble(1);
                String def_location = rs.getString(2);
                int paying_order_id = rs.getInt(3);
                ps = conn.prepareStatement("update orders set receipt_place = ? where order_id = ?");
                ps.setString(1, address);
                ps.setInt(2, paying_order_id);
                ps.executeUpdate();
                ps = conn.prepareStatement("update customer set purchase_sum = purchase_sum + ? where customer_id = ?");// 更新总金额
                ps.setDouble(1, order_price_sum);
                ps.setString(2, customerId);
                ps.executeUpdate();
                ps = conn.prepareStatement("select purchase_sum from customer where customer_id = ?");
                ps.setString(1, customerId);
                rs = ps.executeQuery();
                rs.next();
                double purchase_sum = rs.getDouble(1);
                int level = 0;
                while (purchase_sum > 1000.0 && level <= 6){
                    level += 1;
                    purchase_sum -= 1000.0;
                }
                ps = conn.prepareStatement("INSERT INTO orders (update_time,receipt_place," +
                        "customer_id,price_sum) values(CURRENT_TIME(),?,?,?)");
                ps.setString(1, def_location);
                ps.setString(2, customerId);
                ps.setDouble(3, 0.0);
                ps.executeUpdate();
                ps = conn.prepareStatement("select order_id from orders where customer_id = ? order by order_id desc limit 1;");
                ps.setString(1, customerId);
                rs = ps.executeQuery();
                if(rs.next()){
                    ps = conn.prepareStatement("update customer set current_order_id = ?,level = ? where customer_id=?");
                    ps.setInt(1, rs.getInt(1));
                    ps.setInt(2, level);
                    ps.setString(3, customerId);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
    }

//    public void payOrder(String customerId) {
//        Connection conn = dbConnectUtil.connect();
//        PreparedStatement ps;
//        ResultSet rs;
//        try {
//            Customer customer = new Customer();
//            ps = conn.prepareStatement("select orders.* from orders,customer " +
//                    "where orders.customer_id=customer.customer_id and customer.customer_id=?");
//            ps.setString(1, customerId);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                customer.setID(customerId);
//                List<Order> orderList = new ArrayList<>();
//                Order order = new Order(rs.getString(1), customer
//                        , rs.getString(2), rs.getString(3),
//                        rs.getDouble(5), 0, new ArrayList<>());
//                orderList.add(order);
//                customer.setOrders(orderList);
//            }
//            ps = conn.prepareStatement("select level,purchase_sum from customer " +
//                    "where customer_id=?;");
//            rs = ps.executeQuery();
//            customer.setTotalCost(rs.getDouble(2));
//            customer.setVipLevel(rs.getInt(1));
//            customer.upgradeVip();
//            ps = conn.prepareStatement("update UPDATE customer SET level=?,purchase_sum=? " +
//                    "where customer_id=?;");
//            ps.setDouble(1, customer.getVipLevel());
//            ps.setDouble(2, customer.getTotalCost());
//            ps.setString(3 ,customerId);
//            ps.executeUpdate();
//            ps = conn.prepareStatement("INSERT INTO orders update_time,receipt_place," +
//                    "customer_id,price_sum values(CURRENT_TIME(),?,?,?)");
//
//            ps.setString(1, customer.getLocation());
//            ps.setString(2, customerId);
//            ps.setDouble(3, 0.0);
//            ps.executeUpdate();
////            ps = conn.prepareStatement("INSERT INTO orders order_id,update_time,receipt_place," +
////                    "customer_id,price_sum values(?,CURRENT_TIME(),?,?,?)");
////            ps.setInt(1, customer.getOrders().size() + 1);
////            ps.setString(2, customer.getLocation());
////            ps.setString(3, customerId);
////            ps.setDouble(4, 0.0);
////            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public List<OrderBook> checkOrder(String customerId) {
        List<OrderBook> orderBookList = new ArrayList<>();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            ps = conn.prepareStatement("select book_name, price " +
//                    "from orders natural join order_book natural join" +
//                    "book where customer_id = ?;");
            ps = conn.prepareStatement("select book.book_name, book.price, order_book.*, orders.* " +
                    "from book natural join order_book natural join orders " +
                    "natural join customer where customer.current_order_id = order_id and customer.customer_id = ?;");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderBook orderBook = new OrderBook(rs.getString(3),rs.getInt(4), rs.getInt(5), rs.getString(1), rs.getDouble(2));
                orderBookList.add(orderBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return orderBookList;
    }

    public void addOrderBook(String customerId, String isbn, int quantity) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
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
                        ps.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);

        }
    }

    public void changeOrderBook(String customerId, String isbn, int quantity) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps=null;
        ResultSet rs=null;
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


                if (rs.next()) {
                    int price = rs.getInt(1);
                    ps = conn.prepareStatement("select * from order_book where ISBN = ? and order_id = ?");//查询order_book
                    ps.setString(1, isbn);
                    ps.setInt(2, orderId);
                    rs = ps.executeQuery();
                    if (rs.next()) {// 若已存在，则更新
                        int change_num = quantity - rs.getInt(3);
                        ps = conn.prepareStatement("UPDATE order_book SET order_sum=? where ISBN = ? and order_id=?");
                        ps.setInt(1, quantity);
                        ps.setString(2, isbn);
                        ps.setInt(3, orderId);
                        ps.executeUpdate();
                        ps = conn.prepareStatement("select orders.price_sum,customer.level from " +
                                "orders natural join customer where order_id = ?");
                        ps.setInt(1, orderId);
                        rs = ps.executeQuery();
                        if (rs.next()){
                            double finalMoney = price * change_num * (1-0.1* (double) rs.getInt(2))
                                    + rs.getDouble(1);
                            ps = conn.prepareStatement("UPDATE orders SET update_time=CURRENT_TIME(),price_sum=? " +
                                    "where order_id=?");
                            ps.setDouble(1, finalMoney);
                            ps.setInt(2, orderId);
                            ps.executeUpdate();
                        }
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);

        }
    }

    public void deleteOrderBook(int order_id, String isbn, String account) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = conn.prepareStatement("select book.price, order_sum from book natural join order_book where isbn = ? and order_id = ?");
            ps.setString(1, isbn);
            ps.setInt(2, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                double origin_order_book_take = rs.getDouble(1) * rs.getInt(2);
                ps = conn.prepareStatement("select level from customer where customer_id = ?");
                ps.setString(1, account);
                rs.close();
                rs = ps.executeQuery();
                rs.next();
                double order_book_take = (1.0- 0.1*rs.getInt(1))*origin_order_book_take;
                ps = conn.prepareStatement("update orders set price_sum = price_sum - ? where order_id = ?");
                ps.setDouble(1, order_book_take);
                ps.setInt(2, order_id);
                ps.executeUpdate();
                ps = conn.prepareStatement("delete from order_book where isbn = ? and order_id = ?");
                ps.setString(1, isbn);
                ps.setInt(2, order_id);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
    }

//    public void deleteOrderBook(String customerId, String isbn) {
//        Connection conn = dbConnectUtil.connect();
//        PreparedStatement ps;
//        ResultSet rs;
//        try {
//            ps = conn.prepareStatement("select order_sum,order_id from order_book natural join orders natural join customer where current_order_id = orders and ISBN=?;");
//            ps.setString(1, isbn);
//            rs = ps.executeQuery();
//            String orderId = rs.getString(2);
//            int orderSum = rs.getInt(1);
//            orderSum -= 1;
//            if (0 == orderSum) {
//                ps = conn.prepareStatement("DELETE from order_book where ISBN=?;");
//                ps.setString(1, isbn);
//                ps.executeQuery();
//            } else {
//                ps = conn.prepareStatement("UPDATE order_book SET order_sum=? where order_id=?");
//                ps.setInt(1, orderSum);
//                ps.setString(2, orderId);
//                ps.executeQuery();
//            }
//
//            ps = conn.prepareStatement("select price from book where ISBN=?;");
//            ps.setString(1, isbn);
//            rs = ps.executeQuery();
//            double price = rs.getDouble(1);
//
//            ps = conn.prepareStatement("select orders.price_sum,customer.current_order_id " +
//                    "from orders,customer where customer.customer_id=? and " +
//                    "customer.current_order_id = orders.order_id;");
//            ps.setString(1, customerId);
//            rs = ps.executeQuery();
//            double finalMoney = rs.getDouble(1) - price;
//            ps = conn.prepareStatement("UPDATE orders SET update_time=CURRENT_TIME(),price_sum=? " +
//                    "where order_id=?");
//            ps.setDouble(1, finalMoney);
//            ps.setString(2, orderId);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public Order getOrderByCustomer(String customerId) {
        Order order = new Order();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select orders.* from orders,customer " +
                    "where customer.customer_id = ? and " +
                    "customer.current_order_id = orders.order_id;");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            Customer customer = new Customer();
            if(rs.next()){
                customer.setID(rs.getString(4));
                order = new Order(rs.getString(1),
                        customer,
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(5), 0,
                        new ArrayList<>());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);

        }
        return order;
    }
}
