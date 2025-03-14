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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    public void bookAppointment(String appointmentID) {
        System.out.println(name + " has booked an appointment with ID: " + appointmentID);
    }

    public void cancelAppointment(String appointmentID) {
        System.out.println(name + " has canceled the appointment with ID: " + appointmentID);
    }
}