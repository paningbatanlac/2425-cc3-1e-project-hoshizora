package com.projectapp.models;

public class Payment {
    private String paymentID;
    private double amount;
    private String method;
    private String status;

    public Payment(String paymentID, double amount, String method, String status) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public void processPayment() {
        if (status.equals("Pending")) {
            status = "Paid";
            System.out.println("Payment processed successfully.");
        } else {
            System.out.println("Payment cannot be processed. Current status: " + status);
        }
    }

    public void refundPayment() {
        if (status.equals("Paid")) {
            status = "Failed";
            System.out.println("Payment refunded successfully.");
        } else {
            System.out.println("Payment cannot be refunded. Current status: " + status);
        }
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}