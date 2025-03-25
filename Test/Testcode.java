import java.util.ArrayList;
import java.util.List;

class Appointment {
    private String patient;
    private String date;
    private String time;

    public Appointment(String patient, String date, String time) {
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public String getPatient() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Appointment{" + "Patient='" + patient + "', Date='" + date + "', Time='" + time + "'}";
    }
}

class AppointmentScheduler {
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(String patient, String date, String time) {
        appointments.removeIf(a -> a.getPatient().equals(patient) && a.getDate().equals(date) && a.getTime().equals(time));
    }

    public void displayAppointments() {
        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        AppointmentScheduler scheduler = new AppointmentScheduler();

        scheduler.addAppointment(new Appointment("Alice", "2025-04-10", "10:00 AM"));
        scheduler.addAppointment(new Appointment("Bob", "2025-04-11", "2:00 PM"));

        scheduler.displayAppointments();

        scheduler.removeAppointment("Alice", "2025-04-10", "10:00 AM");

        scheduler.displayAppointments();
    }
}
