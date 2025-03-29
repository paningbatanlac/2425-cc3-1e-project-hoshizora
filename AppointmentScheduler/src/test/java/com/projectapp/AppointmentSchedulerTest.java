import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentSchedulerTest {
    private AppointmentScheduler scheduler;

    @BeforeEach
    public void setUp() {
        // Initialize the AppointmentScheduler before each test
        scheduler = new AppointmentScheduler();
    }

    @Test
    public void testAddAppointment() {
        // Arrange
        User client = new User("C001", "John Doe", "john@example.com", "1234567890", "Client");
        User staff = new User("S001", "Jane Smith", "jane@example.com", "0987654321", "Staff");
        Service service = new Service("SRV001", "Consultation", "Initial consultation service", 100.0);
        Appointment newAppointment = new Appointment("1", "2023-10-01", "10:00", client, staff, service);

        // Act
        scheduler.addAppointment(newAppointment);
        ArrayList<Appointment> appointments = scheduler.loadAppointments();

        // Assert
        assertEquals(1, appointments.size());
        assertEquals("John Doe", appointments.get(0).getClient().getName());
    }

    @Test
    public void testEditAppointment() {
        // Arrange
        User client = new User("C001", "John Doe", "john@example.com", "1234567890", "Client");
        User staff = new User("S001", "Jane Smith", "jane@example.com", "0987654321", "Staff");
        Service service = new Service("SRV001", "Consultation", "Initial consultation service", 100.0);
        Appointment newAppointment = new Appointment("1", "2023-10-01", "10:00", client, staff, service);
        scheduler.addAppointment(newAppointment);

        // Act
        Appointment updatedAppointment = new Appointment("1", "2023-10-02", "11:00", client, staff, service);
        scheduler.editAppointment(0, updatedAppointment);
        ArrayList<Appointment> appointments = scheduler.loadAppointments();

        // Assert
        assertEquals("2023-10-02", appointments.get(0).getDate());
        assertEquals("11:00", appointments.get(0).getTime());
    }

    @Test
    public void testDeleteAppointment() {
        // Arrange
        User client = new User("C001", "John Doe", "john@example.com", "1234567890", "Client");
        User staff = new User("S001", "Jane Smith", "jane@example.com", "0987654321", "Staff");
        Service service = new Service("SRV001", "Consultation", "Initial consultation service", 100.0);
        Appointment newAppointment = new Appointment("1", "2023-10-01", "10:00", client, staff, service);
        scheduler.addAppointment(newAppointment);

        // Act
        scheduler.deleteAppointment(0);
        ArrayList<Appointment> appointments = scheduler.loadAppointments();

        // Assert
        assertEquals(0, appointments.size());
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test if necessary
        // For example, you might want to clear the appointments or reset the state
    }
}