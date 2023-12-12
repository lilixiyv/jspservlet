package com.jspservlet.entity;

import java.util.List;

public class PublishHouse {
    private String name;
    private String location;
    private Integer totalPublish;

    private List<Book> bookList;

    public PublishHouse() {
    }

    public PublishHouse(String name, String location, Integer totalPublish, List<Book> bookList) {
        this.name = name;
        this.location = location;
        this.totalPublish = totalPublish;
        this.bookList = bookList;
    }

    public Integer getTotalPublish() {
        return this.totalPublish;
    }

    public String getLocation() {
        return this.location;
    }

    public String getName() {
        return this.name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPublish(Integer totalPublish) {
        this.totalPublish = totalPublish;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
