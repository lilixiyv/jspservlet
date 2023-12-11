package com.jspservlet.entity;

import java.util.List;

public class Category {
    private String categoryName;
    private Integer totalNumber;
    private List<Book> bookList;

    public Category() {
    }

    public Category(String categoryName, Integer totalNumber, List<Book> bookList) {
        this.categoryName = categoryName;
        this.totalNumber = totalNumber;
        this.bookList = bookList;
    }

    public String getCategoryName() {
        return this.categoryName;
    }


    public Integer getTotalNumber() {
        return this.totalNumber;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
