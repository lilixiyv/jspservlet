package com.jspservlet.entity;

import java.util.List;

public class Author {
    private String name;
    private String nationality;
    private String birthDay; // format: 2000-01-01
    private String description;
    private Integer publishNumber;
    private List<Book> publishBook;

    public Author(String name, String nationality, String birthDay, String description,
                  Integer publishNumber, List<Book> publishBook) {
        this.name = name;
        this.nationality = nationality;
        this.birthDay = birthDay;
        this.description = description;
        this.publishNumber = publishNumber;
        this.publishBook = publishBook;
    }

    public Author() {
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateNationality(String nationality) {
        this.nationality = nationality;
    }

    public void updateBirthDay(String birthday) {
        this.birthDay = birthday;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updatePublishBook(List<Book> publishBook) {
        this.publishBook = publishBook;
    }

    public void updatePublishNumber(Integer number) {
        this.publishNumber = number;
    }

    public Integer getPublishNumber() {
        return this.publishNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getBirthDay() {
        return this.birthDay;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Book> getPublishBook() {
        return this.publishBook;
    }
}
