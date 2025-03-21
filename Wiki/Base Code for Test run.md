import java.util.*;

class Appointment {
    private String title;
    private Date date;
    private String location;
    
    public Appointment(String title, Date date, String location) {
        this.title = title;
        this.date = date;
        this.location = location;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getDetails() {
        return "Appointment: " + title + " at " + location + " on " + date;
    }
}

class AppointmentScheduler {
    private List<Appointment> appointments = new ArrayList<>();
    
    public void addAppointment(String title, Date date, String location) {
        appointments.add(new Appointment(title, date, location));
    }
    
    public void removeAppointment(String title) {
        appointments.removeIf(a -> a.getTitle().equalsIgnoreCase(title));
    }
    
    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(a.getDetails());
            }
        }
    }
}

public class GeneralAppointmentSystem {
    public static void main(String[] args) {
        AppointmentScheduler scheduler = new AppointmentScheduler();
        
        scheduler.addAppointment("Project Meeting", new Date(), "Office");
        scheduler.addAppointment("Doctor's Checkup", new Date(), "Hospital");
        
        System.out.println("Appointments:");
        scheduler.listAppointments();
        
        scheduler.removeAppointment("Project Meeting");
        
        System.out.println("Updated Appointments:");
        scheduler.listAppointments();
    }
}
