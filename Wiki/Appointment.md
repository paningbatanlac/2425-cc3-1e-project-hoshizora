# 2425-cc3-1e-project-hoshizora
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
        this.status = "Scheduled";
    }

    public void confirmAppointment() {
        this.status = "Scheduled";
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
        return "Appointment ID: " + appointmentID + "\nDate: " + date + "\nTime: " + time + 
               "\nService: " + service.getName() + "\nClient: " + client.getName() + 
               "\nStatus: " + status;
    }
}
