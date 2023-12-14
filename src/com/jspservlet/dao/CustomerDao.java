package com.jspservlet.dao;

import com.jspservlet.entity.*;
import com.jspservlet.util.dbConnectUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends UserDao {

    public Customer userProfile(String customer_id, String nickname, String mail_id,
                                String telephone, String def_location, String password) throws NoSuchAlgorithmException {
        Sha256 encrypt = new Sha256();
        List<Order> orders = new ArrayList<>();
        return new Customer(nickname, encrypt.sha256(password), customer_id,
                mail_id, telephone, def_location, 0, orders, 0.0);
    }

    public boolean register(String customer_id, String nickname, String mail_id,
                            String telephone, String def_location, String password) throws NoSuchAlgorithmException {

        Customer user = this.userProfile(customer_id, nickname, mail_id,
                telephone, def_location, password);

        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from customer where customer_id = ?");
            ps.setString(1, user.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            } else {
                ps = conn.prepareStatement("insert into customer values (?, ?, ?, ?, ?, 0, 0, 'normal', null, ?);");
                ps.setString(1,customer_id);
                ps.setString(2,user.getName());
                ps.setString(3,user.getPassword());
                ps.setString(4, mail_id);
                ps.setString(5, telephone);

                ps.setString(6, def_location);

                ps.executeUpdate();
                ps = conn.prepareStatement("INSERT INTO orders (update_time,receipt_place," +
                        "customer_id,price_sum) values(CURRENT_TIME(),?,?,?)");
                ps.setString(1, def_location);
                ps.setString(2, customer_id);
                ps.setDouble(3, 0.0);
                ps.executeUpdate();
                ps = conn.prepareStatement("select order_id from orders where customer_id = ? order by order_id desc limit 1;");
                ps.setString(1, customer_id);
                rs = ps.executeQuery();
                if(rs.next()){
                    ps = conn.prepareStatement("update customer set current_order_id = ? where customer_id=?");
                    ps.setInt(1, rs.getInt(1));
                    ps.setString(2, customer_id);
                    ps.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return false;
    }

    public List<Book> screen(String title, Integer publishDateStart, Integer publishDateEnd, String authorName,
                             String publishHouseName, String categoryName, Double rateStart, Double rateEnd,
                             Integer commentNumberStart, Integer commentNumberEnd, Double priceStart, Double priceEnd,
                             String type, Integer rise) {
        Integer[] publishDate = {publishDateStart, publishDateEnd};
        Double[] rate = {rateStart, rateEnd};
        Integer[] commentNumber = {commentNumberStart, commentNumberEnd};
        Double[] price = {priceStart, priceEnd};
        return new BookControl().inquire(title, publishDate, authorName, publishHouseName, categoryName,
                rate, commentNumber, price, type, rise);
    }

    public List<Book> allBook() throws SQLException {
        return (new BookControl()).selectAll();
    }

    public int getVipLevel(String customerId) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select level from customer where customer_id= ? ");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return 0;
    }

    public Book showIsbn(String isbn) {
        return new BookControl().selectByIsbn(isbn);
    }

    public Author showAuthor(String authorName) {
        return new BookControl().selectByAuthor(authorName);
    }

    public PublishHouse showPublishHouse(String name) {
        return new BookControl().selectByPublishHouse(name);
    }

    public Category showCategory(String categoryName) {
        return new BookControl().selectByCategory(categoryName);
    }

    public void payOrder(String customerId, String address) {
        new OrderControl().payOrder(customerId, address);
    }

    public List<OrderBook> getCurrentOrder(String customerId) {
        return new OrderControl().checkOrder(customerId);
    }

    public void addAndDelete(String type, String customerId, String isbn, int quantity) {
        if (type.equals("add")) {
            new OrderControl().addOrderBook(customerId, isbn, quantity);
        }
    }

    public Order getOrderByCustomer(String customerId) {
        return new OrderControl().getOrderByCustomer(customerId);
    }

    public Book ShowNotBuyBook(String customerId) {
        return new BoughtBookControl().notBuyBook(customerId);
    }

    public Order GetOrderBook(String customerId) {
        return new BoughtBookControl().getOrderBook(customerId);
    }

    public List<Order> GetHistoryOrder(String customerId) throws SQLException {
        return new HistoricalControl().getHistoryOrder(customerId);
    }

    public Customer GetAccountInfo (String cutomerId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = dbConnectUtil.connect();

            ps = conn.prepareStatement("select * from customer where customer_id = ?");
            ps.setString(1, cutomerId);
            rs = ps.executeQuery();
            if (rs.next()){
                return new Customer(rs.getString(2), rs.getString(3), rs.getString(1), rs.getString(4), rs.getString(5), rs.getString(10), rs.getInt(6), null, rs.getDouble(7));
            }
        dbConnectUtil.disconnect(conn, ps, rs);
        return null;
    }

    public boolean ChangeAccountInfo (String customer_id, String change_account, String username, String email, String phone_number, String address) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = dbConnectUtil.connect();

        if(customer_id.equals(change_account)) {
            ps = conn.prepareStatement("update customer set nickname=?, mail_id=?, telephone=?, def_location=? where customer_id = ?");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, phone_number);
            ps.setString(4, address);
            ps.setString(5, customer_id);
            ps.executeUpdate();
            return false;
        } else {
            ps = conn.prepareStatement("select * from customer where customer_id = ?");
            ps.setString(1, change_account);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            } else {
                ps = conn.prepareStatement("update customer set nickname=?, mail_id=?, telephone=?, def_location=?, customer_id=? where customer_id=?");
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, phone_number);
                ps.setString(4, address);
                ps.setString(5, change_account);
                ps.setString(6, customer_id);
                ps.executeUpdate();
                return false;
            }
        }

    }
    public boolean ChangeAccountInfo (String customer_id, String change_account, String username, String email, String phone_number) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = dbConnectUtil.connect();

        if(customer_id.equals(change_account)) {
            ps = conn.prepareStatement("update customer set nickname=?, mail_id=?, telephone=? where customer_id = ?");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, phone_number);
            ps.setString(4, customer_id);
            ps.executeUpdate();
            dbConnectUtil.disconnect(conn, ps, rs);
            return false;
        } else {
            ps = conn.prepareStatement("select * from customer where customer_id = ?");
            ps.setString(1, change_account);
            rs = ps.executeQuery();
            if(rs.next()){
                dbConnectUtil.disconnect(conn, ps, rs);
                return true;
            } else {
                ps = conn.prepareStatement("update customer set nickname=?, mail_id=?, telephone=?, customer_id=? where customer_id=?");
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, phone_number);
                ps.setString(4, change_account);
                ps.setString(5, customer_id);
                ps.executeUpdate();
                dbConnectUtil.disconnect(conn, ps, rs);
                return false;
            }
        }
    }

    public boolean ChangeAccountInfo(String customer_id, String password, String changed_password) throws NoSuchAlgorithmException, SQLException {
        String sha256_password = new Sha256().sha256(password);
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection conn = dbConnectUtil.connect();
        ps = conn.prepareStatement("select pw from customer where customer_id = ?");
        ps.setString(1, customer_id);
        rs = ps.executeQuery();
        if (rs.next()){
            String correct_pw = rs.getString(1);
            if (correct_pw.equals(sha256_password)){
                String sha256_changed_pw = new Sha256().sha256(changed_password);
                ps = conn.prepareStatement("update customer set pw = ? where customer_id = ?");
                ps.setString(1, sha256_changed_pw);
                ps.setString(2, customer_id);
                ps.executeUpdate();
                dbConnectUtil.disconnect(conn, ps, rs);
                return true;
            } else {
                dbConnectUtil.disconnect(conn, ps, rs);
                return false;
            }
        }
        dbConnectUtil.disconnect(conn, ps, rs);
        return false;
    }
}
