package com.projectapp;

import java.awt.*;
import javax.swing.*;

public class Dashboard {
    private JFrame frame;
    private JPanel panel;
    private JLabel userLabel;
    private JButton viewAppointmentsButton, addAppointmentButton, settingsButton, logoutButton;
    private JTextArea upcomingAppointmentsArea, notificationArea;
    private JLabel welcomeLabel;
    private User loggedInUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().createUI());
    }

    // Constructor for initializing the dashboard
    public Dashboard() {
        loggedInUser = new User("John Doe"); // Example user
    }

    // Create the UI components
    public void createUI() {
        frame = new JFrame("Dashboard (Home)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null); // Center the frame

        panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20)); // Increased spacing for a cleaner look
        panel.setBackground(new Color(230, 230, 255)); // Soft background color

        // Header: Welcome Section
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Blue background for header
        welcomeLabel = new JLabel("Welcome, " + loggedInUser.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE); // White text for contrast
        headerPanel.add(welcomeLabel);
        panel.add(headerPanel, BorderLayout.NORTH);

        // Notifications Panel
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BorderLayout());
        notificationPanel.setBackground(Color.WHITE);

        JLabel notificationLabel = new JLabel("Notifications:");
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        notificationArea = new JTextArea("No new notifications."); // Placeholder for notifications
        notificationArea.setEditable(false);
        JScrollPane notificationScroll = new JScrollPane(notificationArea);

        notificationPanel.add(notificationLabel, BorderLayout.NORTH);
        notificationPanel.add(notificationScroll, BorderLayout.CENTER);
        panel.add(notificationPanel, BorderLayout.WEST);

        // Upcoming Appointments Section
        JPanel upcomingPanel = new JPanel();
        upcomingPanel.setLayout(new BorderLayout());
        upcomingPanel.setBackground(Color.WHITE);

        JLabel upcomingLabel = new JLabel("Upcoming Appointments:");
        upcomingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        upcomingAppointmentsArea = new JTextArea("No upcoming appointments."); // Placeholder
        upcomingAppointmentsArea.setEditable(false);
        JScrollPane upcomingScroll = new JScrollPane(upcomingAppointmentsArea);

        upcomingPanel.add(upcomingLabel, BorderLayout.NORTH);
        upcomingPanel.add(upcomingScroll, BorderLayout.CENTER);
        panel.add(upcomingPanel, BorderLayout.CENTER);

        // Quick Action Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Centered buttons with spacing

        viewAppointmentsButton = new JButton("View Appointments");
        addAppointmentButton = new JButton("Add Appointment");
        settingsButton = new JButton("Settings");
        logoutButton = new JButton("Logout");

        buttonPanel.add(viewAppointmentsButton);
        buttonPanel.add(addAppointmentButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners for Buttons
        viewAppointmentsButton.addActionListener(e -> viewAppointments());
        addAppointmentButton.addActionListener(e -> addAppointment());
        settingsButton.addActionListener(e -> openSettings());  // Open Settings when clicked
        logoutButton.addActionListener(e -> logout());

        // Final setup
        frame.add(panel);
        frame.setVisible(true);
    }

    // View Appointments: Show a list of appointments (This could be extended later)
    private void viewAppointments() {
        String appointments = "1. Appointment with Jane Smith\n2. Appointment with Alex Brown\n3. Appointment with John Doe"; // Placeholder data
        JOptionPane.showMessageDialog(frame, "Upcoming Appointments:\n" + appointments);
    }

    // Add Appointment: Placeholder for adding an appointment
    private void addAppointment() {
        // Open a new dialog or form to add an appointment (for simplicity, we'll show a message for now)
        String newAppointment = JOptionPane.showInputDialog(frame,
                "Enter new appointment details (e.g., Client Name, Date, Time):");
        if (newAppointment != null && !newAppointment.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Appointment added: " + newAppointment);
        } else {
            JOptionPane.showMessageDialog(frame, "No appointment added.");
        }
    }

    // Open Settings: Now opens the actual SettingsPage window
    private void openSettings() {
        new SettingsPage().setVisible(true);  // Open the SettingsPage when button is clicked
    }

    // Logout: Handle user logout
    private void logout() {
        // Handle logout (for now, just exit the app)
        int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "Logging out...");
            System.exit(0);
        }
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
