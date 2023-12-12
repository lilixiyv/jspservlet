package com.jspservlet.entity;

public class Administrator extends User {
    public Administrator(String name, String password, String id, String email,
                         String telephone, String location) {
        super(name, password, id, email, telephone, location);
    }

    public Administrator() {
    }
}

