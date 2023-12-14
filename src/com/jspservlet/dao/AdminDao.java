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

public class AdminDao extends UserDao {
    public List<Customer> getAllCustomerInformation()
            throws SQLException {
        return new ManageAccount().getCustomerInformation();
    }

    public List<Administrator> getAdminInformation()
            throws SQLException {
        return new ManageAccount().getAdminInformation();
    }

    public boolean addAdmin(String name, String Id, String password,
                         String email, String telephone, String location)
            throws SQLException, NoSuchAlgorithmException {
        password = new Sha256().sha256(password);
        Administrator administrator = new Administrator(name, password, Id,
                email, telephone, location);
        return new ManageAccount().addAdministrator(administrator);
    }

    public List<Book> getAllBook() {
        return new BookControl().selectAll();
    }

    public void upDateBookInformation(Book book, Category category,
                                      Author author, PublishHouse publishHouse) {
        new BookControl().addBook(book, category, author, publishHouse);
    }

    public void addBookInfo(String isbn, String book_name, String book_description, String time, String press_name, String author_name, String category_name, Double price) throws SQLException {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps;
        ps = conn.prepareStatement("insert into book values(?, ?, ?, ?, 0, 0, ?, ?, ?, ?)");
        ps.setString(1, isbn);
        ps.setString(2, book_name);
        ps.setString(3, book_description);
        ps.setString(4, time);
        ps.setString(5, press_name);
        ps.setString(6, author_name);
        ps.setString(7, category_name);
        ps.setDouble(8, price);
        ps.executeUpdate();
        ps = conn.prepareStatement("update author set pub_sum = pub_sum + 1 where author_name = ?");
        ps.setString(1, author_name);
        ps.executeUpdate();
        ps = conn.prepareStatement("update category set sum = sum + 1 where category_name = ?");
        ps.setString(1, category_name);
        ps.executeUpdate();
        ps = conn.prepareStatement("update press set all_sum = all_sum + 1 where press_name = ?");
        ps.setString(1, press_name);
        ps.executeUpdate();
    }

}
