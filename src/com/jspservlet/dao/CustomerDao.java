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
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("select * from customer where customer_id = ?");
            ps.setString(1, user.getID());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Book> allBook() {
        return (new BookControl()).selectAll();
    }

    public Integer getVipLevel(String customerId) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("select level from customer where customer_id= ? ");
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            return (int) (rs.getDouble(1));
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void payOrder(String customerId) {
        new OrderControl().payOrder(customerId);
    }

    public List<Book> getCurrentOrder(String customerId) {
        return new OrderControl().checkOrder(customerId);
    }

    public void addAndDelete(String type, String customerId, String isbn) {
        if (type.equals("add")) {
            new OrderControl().addOrderBook(customerId, isbn);
        } else {
            new OrderControl().deleteOrderBook(customerId, isbn);
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
}