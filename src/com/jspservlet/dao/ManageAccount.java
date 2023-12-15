package com.jspservlet.dao;

import com.jspservlet.entity.Administrator;
import com.jspservlet.entity.Customer;
import com.jspservlet.entity.Order;
import com.jspservlet.entity.Sha256;
import com.jspservlet.util.dbConnectUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageAccount {
    public List<Customer> getCustomerInformation()
            throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        ps = conn.prepareStatement("select * from customer where identity='normal';");
        rs = ps.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setID(rs.getString(1));
            customer.setName(rs.getString(2));
            customer.setPassword(rs.getString(3));
            customer.setEmail(rs.getString(4));
            customer.setTelephone(rs.getString(5));
            customer.setVipLevel(rs.getInt(6));
            customer.setTotalCost(rs.getDouble(7));
            List<Order> orderList = new ArrayList<>();
            Order order = new Order();
            order.setOrderNumber(rs.getString(9));
            orderList.add(order);
            customer.setOrders(orderList);
            customer.setLocation(rs.getString(10));
            customerList.add(customer);
        }
        dbConnectUtil.disconnect(conn, ps, rs);
        return customerList;
    }

    public void changeInformation(String input, String type, String customerId)
            throws SQLException, NoSuchAlgorithmException {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ps = conn.prepareStatement("UPDATE customer SET ? = ? where customer_id = ?;");
        ps.setString(1, type);
        if (type.equals("pw")) {
            input = new Sha256().sha256(input);
            ps.setString(2, input);
            ps.setString(3, customerId);
            ps.executeUpdate();
        }
        dbConnectUtil.disconnect(conn, ps, null);
    }

    public List<Administrator> getAdminInformation()
            throws SQLException {
        List<Administrator> adminList = new ArrayList<>();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        ps = conn.prepareStatement("select * from customer where identity='super';");
        rs = ps.executeQuery();
        while (rs.next()) {
            Administrator admin = new Administrator();
            admin.setName(rs.getString(2));
            admin.setID(rs.getString(1));
            admin.setEmail(rs.getString(4));
            admin.setLocation(rs.getString(10));
            admin.setTelephone(rs.getString(5));
            adminList.add(admin);
        }
        dbConnectUtil.disconnect(conn, ps, rs);
        return adminList;
    }

    public boolean addAdministrator(Administrator admin) throws SQLException{
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;

//        ps = conn.prepareStatement("insert into customer " +
//                "customer_id, nickname, pw, mail_id, telephone, level, " +
//                "purchase_sum, identity, current_order_id, def_location " +
//                "values (?, ?, ?, ?, ?, 0, 0, 'super', 0, ?);");
        ps = conn.prepareStatement("select * from customer where customer_id = ?");
        ps.setString(1, admin.getId());
        rs = ps.executeQuery();
        if(rs.next()){
            dbConnectUtil.disconnect(conn, ps, rs);
            return true;
        } else {
            ps = conn.prepareStatement("insert into customer (customer_id, nickname, pw, mail_id, telephone, identity)" +
                    "values (?, ?, ?, ?, ?, 'super');");
            ps.setString(1, admin.getId());
            ps.setString(2, admin.getName());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getEmail());
            ps.setString(5, admin.getTelephone());
            ps.executeUpdate();
            dbConnectUtil.disconnect(conn, ps, rs);
            return false;
        }
    }
}
