package com.jspservlet.entity;

import java.util.List;

public class Order {
    private String orderNumber;
    private Customer customer;
    private String updateTime;
    private String address; // 2000-01-01
    private Double price;
    private Integer amount;

    private List<Book> bookList;

    public Order() {
    }

    public Order(String orderNumber, Customer customer, String updateTime,
                 String address, Double price, Integer amount, List<Book> bookList) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.updateTime = updateTime;
        this.address = address;
        this.price = price;
        this.amount = amount;
        this.bookList = bookList;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getAddress() {
        return this.address;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
