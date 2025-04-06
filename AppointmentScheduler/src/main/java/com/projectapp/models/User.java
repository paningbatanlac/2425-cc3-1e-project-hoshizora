package com.projectapp.models;

public class User {
    private String userID;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String password;

    public User(String userID, String name, String email, String phone, String role, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}