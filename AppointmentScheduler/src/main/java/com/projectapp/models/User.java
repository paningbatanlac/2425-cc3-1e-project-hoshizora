package com.projectapp.models;

public class User {
    private String userID;
    private String name;
    private String email;
    private String phone;
    private String role;

    public User(String userID, String name, String email, String phone, String role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public String getUser ID() {
        return userID;
    }

    public void setUser ID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void displayUser Info() {
        System.out.println("User  ID: " + userID + " | Name: " + name + " | Email: " + email + " | Phone: " + phone + " | Role: " + role);
    }
}