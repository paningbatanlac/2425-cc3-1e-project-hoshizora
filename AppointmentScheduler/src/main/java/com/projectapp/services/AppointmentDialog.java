package com.projectapp.services;

import javax.swing.*;
import java.awt.*;

public class AppointmentDialog extends JDialog {
    private JTextField appointmentIDField, dateField, timeField;
    private JComboBox<User> clientComboBox, staffComboBox;
    private JComboBox<Service> serviceComboBox;
    private Appointment appointment;

    public AppointmentDialog(JFrame parent, String title, Appointment app) {
        super(parent, title, true);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Appointment ID:"));
        appointmentIDField = new JTextField();
        add(appointmentIDField);

        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField();
        add(timeField);

        add(new JLabel("Client:"));
        clientComboBox = new JComboBox<>(); // Populate with User instances
        add(clientComboBox);

        add(new JLabel("Staff:"));
        staffComboBox = new JComboBox<>(); // Populate with User instances
        add(staffComboBox);

        add(new JLabel("Service:"));
        serviceComboBox = new JComboBox<>(); // Populate with Service instances
        add(serviceComboBox);

        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.addActionListener(e -> saveAppointment());

        if (app != null) {
            appointmentIDField.setText(app.getAppointmentID());
            dateField.setText(app.getDate());
            timeField.setText(app.getTime());
            clientComboBox.setSelectedItem(app.getClient());
            staffComboBox.setSelectedItem(app.getStaff());
            serviceComboBox.setSelectedItem(app.getService());
        }

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void saveAppointment() {
        String appointmentID = appointmentIDField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        User client = (User ) clientComboBox.getSelectedItem();
        User staff = (User ) staffComboBox.getSelectedItem();
        Service service = (Service) serviceComboBox.getSelectedItem();

        if (!appointmentID.isEmpty() && !date.isEmpty() && !time.isEmpty() && client != null && staff != null && service != null) {
            appointment = new Appointment(appointmentID, date, time, client, staff, service);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled.");
        }
    }

    public Appointment getAppointment() {
        return appointment;
    }
}