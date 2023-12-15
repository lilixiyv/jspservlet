package com.jspservlet.entity;

public class OrderBook {
    private String ISBN;
    private int order_id;
    private int order_sum;
    private String book_name;
    private Double price;

    public OrderBook() {
    }

    public OrderBook(String ISBN, int order_id, int order_sum, String book_name, Double price) {
        this.ISBN = ISBN;
        this.order_id = order_id;
        this.order_sum = order_sum;
        this.book_name = book_name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_sum() {
        return order_sum;
    }

    public void setOrder_sum(int order_sum) {
        this.order_sum = order_sum;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
