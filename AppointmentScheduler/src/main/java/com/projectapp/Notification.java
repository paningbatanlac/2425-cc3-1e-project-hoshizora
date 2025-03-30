package com.projectapp;

public class Notification {
    private String serviceID;
    private String notificationID;
    private String message;
    private User recipient;

    public Notification(String serviceID, String notificationID, String message, User recipient) {
        this.serviceID = serviceID;
        this.notificationID = notificationID;
        this.message = message;
        this.recipient = recipient;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void sendNotification() {
        System.out.println("Notification for Service ID: " + serviceID);
        System.out.println("Sent to: " + recipient.getName());
        System.out.println("Message: " + message);
    }
}
