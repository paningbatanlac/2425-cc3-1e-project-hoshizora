package com.projectapp:

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class AppointmentScheduler {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Appointment> appointments;
    private static final String FILE_NAME = "appointments.json";

    public AppointmentScheduler() {
        appointments = loadAppointments();
        frame = new JFrame("Appointment Scheduler");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        model = new DefaultTableModel(new String[]{"ID", "Client Name", "Date", "Time", "Status"}, 0);
        table = new JTable(model);
        loadTable();
        
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        
        frame.add(panel, BorderLayout.SOUTH);
        
        addButton.addActionListener(e -> addAppointment());
        editButton.addActionListener(e -> editAppointment());
        deleteButton.addActionListener(e -> deleteAppointment());
        
        frame.setVisible(true);
    }

    private void loadTable() {
        model.setRowCount(0);
        for (Appointment app : appointments) {
            model.addRow(new Object[]{app.getAppointmentID(), app.getClient().getName(), app.getDate(), app.getTime(), app.getStatus()});
        }
    }

    private void addAppointment() {
        AppointmentDialog dialog = new AppointmentDialog(frame, "Add Appointment", null);
        Appointment newApp = dialog.getAppointment();
        if (newApp != null) {
            appointments.add(newApp);
            saveAppointments(); // Save after adding
            loadTable();
        }
    }

    private void editAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Appointment existingApp = appointments.get(selectedRow);
            AppointmentDialog dialog = new AppointmentDialog(frame, "Edit Appointment", existingApp);
            Appointment updatedApp = dialog.getAppointment();
            if (updatedApp != null) {
                appointments.set(selectedRow, updatedApp);
                saveAppointments(); // Save after editing
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
            saveAppointments(); // Save after deleting
            loadTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Select an appointment to delete.");
        }
    }

    private ArrayList<Appointment> loadAppointments() {
        ArrayList<Appointment> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        // Check if the file exists
        if (!file.exists()) {
            // Create the file with an empty JSON array if it doesn't exist
            try {
                file.createNewFile(); // Create the file
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // Write an empty JSON array
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // If the file exists, read the appointments from it
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonText.append(line);
                }
                JSONArray jsonArray = new JSONArray(jsonText.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    User client = new User(obj.getString("clientID"), obj.getString("clientName"), "", "", "Client");
                    User staff = new User(obj.getString("staffID"), obj.getString("staffName"), "", "", "Staff");
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
            obj.put("clientID", app.getClient().getUser  ID());
            obj.put("clientName", app.getClient().getName());
            obj.put("staffID", app.getStaff().getUser  ID());
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

    public static void main(String[] args) {
        new AppointmentScheduler();
    }
}