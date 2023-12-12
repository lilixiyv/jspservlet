package com.jspservlet.entity;

import com.jspservlet.dao.ManageAccount;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class User {
    private String name;
    private String password;
    private String id;
    private String email;
    private String telephone;

    private String location;

    public User() {
    }

    public User(String name, String password, String id,
                String email, String telephone, String location) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.email = email;
        this.telephone = telephone;
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getLocation() {
        return this.location;
    }

    public void changeInformation(String input, String type, String id)
            throws SQLException, NoSuchAlgorithmException {
        new ManageAccount().changeInformation(input, type, id);
    }
}