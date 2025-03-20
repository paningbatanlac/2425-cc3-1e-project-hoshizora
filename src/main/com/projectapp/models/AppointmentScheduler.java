import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        model = new DefaultTableModel(new String[]{"ID", "Name", "Date", "Time"}, 0);
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
            model.addRow(new Object[]{app.getId(), app.getName(), app.getDate(), app.getTime()});
        }
    }

    private void addAppointment() {
        AppointmentDialog dialog = new AppointmentDialog(frame, "Add Appointment", null);
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
            AppointmentDialog dialog = new AppointmentDialog(frame, "Edit Appointment", existingApp);
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
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonText.append(line);
                }
                JSONArray jsonArray = new JSONArray(jsonText.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    list.add(new Appointment(obj.getInt("id"), obj.getString("name"), obj.getString("date"), obj.getString("time")));
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
            obj.put("id", app.getId());
            obj.put("name", app.getName());
            obj.put("date", app.getDate());
            obj.put("time", app.getTime());
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

class Appointment {
    private int id;
    private String name, date, time;
    
    public Appointment(int id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getTime() { return time; }
}

class AppointmentDialog extends JDialog {
    private JTextField nameField, dateField, timeField;
    private Appointment appointment;
    
    public AppointmentDialog(JFrame parent, String title, Appointment app) {
        super(parent, title, true);
        setLayout(new GridLayout(4, 2));
        
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);
        
        add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        add(dateField);
        
        add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField();
        add(timeField);
        
        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.addActionListener(e -> saveAppointment());
        
        if (app != null) {
            nameField.setText(app.getName());
            dateField.setText(app.getDate());
            timeField.setText(app.getTime());
        }
        
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    
    private void saveAppointment() {
        String name = nameField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        
        if (!name.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
            int id = (int) (Math.random() * 1000);
            appointment = new Appointment(id, name, date, time);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled.");
        }
    }
    
    public Appointment getAppointment() {
        return appointment;
    }
}
