import java.util.Date;

// User Class
class User {
    private int userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    public User(int userId, String name, String email, String phoneNumber, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void displayUserInfo() {
        System.out.println("User ID: " + userId + " | Name: " + name + " | Email: " + email);
    }
}

// Appointment Class
class Appointment {
    private int appointmentId;
    private int userId;
    private int serviceId;
    private Date date;
    private String time;
    private String status;

    public Appointment(int appointmentId, int userId, int serviceId, Date date, String time, String status) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public void confirmAppointment() {
        this.status = "Confirmed";
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
    }
}

// Payment Class
class Payment {
    private int paymentId;
    private int userId;
    private int appointmentId;
    private double amount;
    private String paymentMethod;
    private String status;

    public Payment(int paymentId, int userId, int appointmentId, double amount, String paymentMethod, String status) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public void processPayment() {
        this.status = "Completed";
    }

    public void refundPayment() {
        this.status = "Refunded";
    }
}

// Service Class
class Service {
    private int serviceId;
    private String serviceName;
    private String description;
    private double price;

    public Service(int serviceId, String serviceName, String description, double price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
    }

    public void displayServiceDetails() {
        System.out.println("Service: " + serviceName + " | Price: $" + price);
    }
}

// Notification Class
class Notification {
    private int notificationId;
    private int userId;
    private String message;
    private Date timestamp;

    public Notification(int notificationId, int userId, String message, Date timestamp) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public void sendNotification() {
        System.out.println("Notification Sent: " + message);
    }
}
