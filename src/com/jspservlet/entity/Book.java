package com.jspservlet.entity;

public class Book {
    private String title;
    private String isbn;
    private PublishHouse publishHouse;
    private Author author;
    private String description;
    private String publishDate; // format 2000-01-01
    private Category category;
    private Integer reviewAmount;
    private Double goodRate;
    private Double price;
    private Integer surplus;

    public Book() {
    }

    public Book(String title, String isbn, PublishHouse publishHouse, Author author, String description, String publishDate,
                Category category, Integer reviewAmount, Double goodRate, Double price, Integer surplus) {
        this.title = title;
        this.isbn = isbn;
        this.publishHouse = publishHouse;
        this.author = author;
        this.description = description;
        this.publishDate = publishDate;
        this.category = category;
        this.reviewAmount = reviewAmount;
        this.goodRate = goodRate;
        this.price = price;
        this.surplus = surplus;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Integer getReviewAmount() {
        return this.reviewAmount;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public String getTitle() {
        return this.title;
    }

    public Double getGoodRate() {
        return this.goodRate;
    }

    public Category getCategory() {
        return this.category;
    }

    public PublishHouse getPublishHouse() {
        return this.publishHouse;
    }

    public Author getAuthor() {
        return this.author;
    }

    public Integer getSurplus() {
        return this.surplus;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setGoodRate(Double goodRate) {
        this.goodRate = goodRate;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublishHouse(PublishHouse publishHouse) {
        this.publishHouse = publishHouse;
    }

    public void setReviewAmount(Integer reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
