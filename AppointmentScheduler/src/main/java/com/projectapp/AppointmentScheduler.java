package com.projectapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.projectapp.models.Appointment;
import com.projectapp.models.Service;
import com.projectapp.models.User;
import com.projectapp.services.AppointmentDialog;

public class AppointmentScheduler {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Appointment> appointments;
    private static final String FILE_NAME = "appointments.json";

    private User[] clients = {
        new User("C001", "John Doe", "john@example.com", "1234567890", "Client", null),
        new User("C002", "Jane Smith", "jane@example.com", "0987654321", "Client", null)
    };

    private User[] staffMembers = {
        new User("S001", "Alice Johnson", "alice@example.com", "1112223333", "Staff", null),
        new User("S002", "Bob Brown", "bob@example.com", "4445556666", "Staff", null)
    };

    private Service[] services = {
        new Service("SRV001", "Consultation", "Initial consultation service", 100.0),
        new Service("SRV002", "Follow-up", "Follow-up consultation service", 50.0)
    };

    public AppointmentScheduler() {
        appointments = loadAppointments();
        frame = new JFrame("Appointment Scheduler");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Appointment Scheduler");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Client Name", "Date", "Time", "Status"}, 0);
        table = new JTable(model);
        loadTable();
        
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        customizeButton(addButton);
        customizeButton(editButton);
        customizeButton(deleteButton);
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addAppointment());
        editButton.addActionListener(e -> editAppointment());
        deleteButton.addActionListener(e -> deleteAppointment());
        
        frame.setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void loadTable() {
        model.setRowCount(0);
        for (Appointment app : appointments) {
            model.addRow(new Object[]{app.getAppointmentID(), app.getClient().getName(), app.getDate(), app.getTime(), app.getStatus()});
        }
    }

    private void addAppointment() {
        AppointmentDialog dialog = new AppointmentDialog(frame, "Add Appointment", null, clients, staffMembers, services);
        Appointment newApp = dialog.getAppointment();
        if (newApp != null) {
            appointments.add(newApp);
            saveAppointments();
            loadTable();
        }
    }

    private void editAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Appointment existingApp = appointments.get(selectedRow);
            AppointmentDialog dialog = new AppointmentDialog(frame, "Edit Appointment", existingApp, clients, staffMembers, services);
            Appointment updatedApp = dialog.getAppointment();
            if (updatedApp != null) {
                appointments.set(selectedRow, updatedApp);
                saveAppointments();
                loadTable();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Select an appointment to edit.");
        }
    }

    private void deleteAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            appointments.remove(selectedRow);
            saveAppointments();
            loadTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Select an appointment to delete.");
        }
    }

    private ArrayList<Appointment> loadAppointments() {
        ArrayList<Appointment> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonText.append(line);
                }
                JSONArray jsonArray = new JSONArray(jsonText.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    User client = new User(obj.getString("clientID"), obj.getString("clientName"), "", "", "Client", line);
                    User staff = new User(obj.getString("staffID"), obj.getString("staffName"), "", "", "Staff", line);
                    Service service = new Service(obj.getString("serviceID"), obj.getString("serviceName"), "", 0);
                    list.add(new Appointment(obj.getString("appointmentID"), obj.getString("date"), obj.getString("time"), client, staff, service));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private void saveAppointments() {
        JSONArray jsonArray = new JSONArray();
        for (Appointment app : appointments) {
            JSONObject obj = new JSONObject();
            obj.put("appointmentID", app.getAppointmentID());
            obj.put("date", app.getDate());
            obj.put("time", app.getTime());
            obj.put("status", app.getStatus());
            obj.put("clientID", app.getClient().getUserID());
            obj.put("clientName", app.getClient().getName());
            obj.put("staffID", app.getStaff().getUserID());
            obj.put("staffName", app.getStaff().getName());
            obj.put("serviceID", app.getService().getServiceID());
            obj.put("serviceName", app.getService().getName());
            jsonArray.put(obj);
        }
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}