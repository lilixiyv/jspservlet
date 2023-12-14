package com.jspservlet.dao;

import com.jspservlet.entity.Customer;
import com.jspservlet.entity.Order;
import com.jspservlet.util.dbConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricalControl {
    public List<Order> getHistoryOrder(String customerId) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        ps = conn.prepareStatement("select orders.* from orders natural join customer where customer.customer_id=? " +
                "and orders.order_id != customer.current_order_id;");
        ps.setString(1, customerId);
        rs = ps.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setOrderNumber(rs.getString(1));
            order.setUpdateTime(rs.getString(2));
            order.setAddress(rs.getString(3));
            Customer customer = new Customer();
            customer.setID(customerId);
            order.setCustomer(customer);
            order.setPrice(rs.getDouble(5));
            orderList.add(order);
        }
        dbConnectUtil.disconnect(conn, ps, rs);
        return orderList;
    }
}
