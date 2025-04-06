package com.projectapp.models;

public class Service {
    private String serviceID;
    private String name;
    
    public Service(String serviceID, String name, String description, double price) {
        this.serviceID = serviceID;
        this.name = name;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getName() {
        return name;
    }
}