package com.jspservlet.dao;

import com.jspservlet.entity.*;

import java.security.NoSuchAlgorithmException;
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

    public void addAdmin(String name, String Id, String password,
                         String email, String telephone, String location)
            throws SQLException, NoSuchAlgorithmException {
        password = new Sha256().sha256(password);
        Administrator administrator = new Administrator(name, password, Id,
                email, telephone, location);
        new ManageAccount().addAdministrator(administrator);
    }

    public List<Book> getAllBook() {
        return new BookControl().selectAll();
    }

    public void upDateBookInformation(Book book, Category category,
                                      Author author, PublishHouse publishHouse) {
        new BookControl().addBook(book, category, author, publishHouse);
    }

}
