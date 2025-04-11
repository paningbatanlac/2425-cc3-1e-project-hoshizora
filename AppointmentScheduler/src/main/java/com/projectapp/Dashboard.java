package com.projectapp;

import java.awt.*;
import javax.swing.*;

import com.projectapp.models.Profile;
import com.projectapp.models.Service;
import com.projectapp.models.SettingsPage;
import com.projectapp.models.User;
import com.projectapp.services.AuthDialog;

public class Dashboard {
    private JFrame frame;
    private JPanel panel;
    private JLabel userLabel;
    private JButton viewAppointmentsButton, addAppointmentButton, settingsButton, logoutButton, profileButton;
    private JTextArea upcomingAppointmentsArea, notificationArea;
    private JLabel welcomeLabel;
    private User loggedInUser ;
    private User[] clients;
    private User[] staffMembers;
    private Service[] services;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().createUI());
    }

    public Dashboard() {
        loggedInUser  = new User("John Doe", "Alice Smith", "", "", "Client", "");

        clients = new User[]{
            new User("1", "Alice Smith", "", "", "Client", ""),
            new User("2", "Bob Johnson", "", "", "Client", "")
        };

        staffMembers = new User[]{
            new User("10", "Dr. Jane", "", "", "Staff", ""),
            new User("11", "Nurse Tom", "", "", "Staff", "")
        };

        services = new Service[]{
            new Service("100", "Consultation", "General medical consultation", 30),
            new Service("101", "Therapy Session", "Mental health therapy", 60)
        };
    }

    public void createUI() {
        frame = new JFrame("Dashboard (Home)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBackground(new Color(230, 230, 255));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        welcomeLabel = new JLabel("Welcome, " + loggedInUser .getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        headerPanel.add(welcomeLabel);
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BorderLayout());
        notificationPanel.setBackground(Color.WHITE);

        JLabel notificationLabel = new JLabel("Notifications:");
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        notificationArea = new JTextArea("No new notifications.");
        notificationArea.setEditable(false);
        JScrollPane notificationScroll = new JScrollPane(notificationArea);

        notificationPanel.add(notificationLabel, BorderLayout.NORTH);
        notificationPanel.add(notificationScroll, BorderLayout.CENTER);
        panel.add(notificationPanel, BorderLayout.WEST);

        JPanel upcomingPanel = new JPanel();
        upcomingPanel.setLayout(new BorderLayout());
        upcomingPanel.setBackground(Color.WHITE);

        JLabel upcomingLabel = new JLabel("Upcoming Appointments:");
        upcomingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        upcomingAppointmentsArea = new JTextArea("No upcoming appointments.");
        upcomingAppointmentsArea.setEditable(false);
        JScrollPane upcomingScroll = new JScrollPane(upcomingAppointmentsArea);

        upcomingPanel.add(upcomingLabel, BorderLayout.NORTH);
        upcomingPanel.add(upcomingScroll, BorderLayout.CENTER);
        panel.add(upcomingPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        viewAppointmentsButton = new JButton("View Appointments");
        addAppointmentButton = new JButton("Add Appointment");
        settingsButton = new JButton("Settings");
        profileButton = new JButton("Profile");
        logoutButton = new JButton("Logout");

        buttonPanel.add(viewAppointmentsButton);
        buttonPanel.add(addAppointmentButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        viewAppointmentsButton.addActionListener(e -> openAppointmentScheduler());
        addAppointmentButton.addActionListener(e -> addAppointment());
        settingsButton.addActionListener(e -> openSettings());
        profileButton.addActionListener(e -> openProfile());
        logoutButton.addActionListener(e -> logout());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void openAppointmentScheduler() {
        new AppointmentScheduler(clients, staffMembers, services);
    }

    private void addAppointment() {
        String newAppointment = JOptionPane.showInputDialog(frame,
                "Enter new appointment details (e.g., Client Name, Date, Time):");
        if (newAppointment != null && !newAppointment.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Appointment added: " + newAppointment);
        } else {
            JOptionPane.showMessageDialog(frame, "No appointment added.");
        }
    }

    private void openSettings() {
        new SettingsPage().setVisible(true);
    }

    private void openProfile() {
        Profile.main(new String[]{});
    }

    private void logout() {
        int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "Logging out...");
            frame.dispose();
            new AuthDialog(null);
        }
    }
}