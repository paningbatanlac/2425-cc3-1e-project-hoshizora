package com.projectapp.services;

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

    public void confirmAppointment() {
        this.status = "Confirmed";
    }

    public void rescheduleAppointment(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
        this.status = "Rescheduled";
    }

    public void cancelAppointment() {
        this.status = "Canceled";
    }

    public String getDetails() {
        return "Appointment ID: " + appointmentID + 
               "\nDate: " + date + 
               "\nTime: " + time + 
               "\nService: " + (service != null ? service.getName() : "N/A") + 
               "\nClient: " + (client != null ? client.getName() : "N/A") + 
               "\nStaff: " + (staff != null ? staff.getName() : "N/A") + 
               "\nStatus: " + status;
    }

    // Getters and Setters
    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}