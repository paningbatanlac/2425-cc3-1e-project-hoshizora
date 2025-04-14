package com.projectapp.models;

public class Appointment {
    private String appointmentID;
    private String date;
    private String time;
    private String status;
    private User client;
    private User staff;
    private Service service;

    public Appointment(String appointmentID, String date, String time, User client, User staff, Service service) {
        this.appointmentID = appointmentID;
        this.date = date;
        this.time = time;
        this.client = client;
        this.staff = staff;
        this.service = service;
        this.status = "Scheduled"; // Default status
    }

    public Appointment(String string, User user, String string2, String string3, String string4) {
        //TODO Auto-generated constructor stub
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public User getClient() {
        return client;
    }

    public User getStaff() {
        return staff;
    }

    public Service getService() {
        return service;
    }
}