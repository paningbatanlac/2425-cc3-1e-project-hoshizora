package com.projectapp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class SettingsPage extends JFrame {
    // UI Components
    private JCheckBox darkModeCheckbox;
    private JCheckBox notificationsCheckbox;
    private JComboBox<String> timeFormatComboBox;
    private JTextField businessHoursStart;
    private JTextField businessHoursEnd;
    private JButton saveButton;
    private JButton cancelButton;

    // Preferences storage
    private Preferences prefs = Preferences.userNodeForPackage(SettingsPage.class);

    public SettingsPage() {
        setTitle("Appointment Scheduler - Settings");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        initializeComponents();
        loadPreferences();

        // Layout
        setupLayout();

        // Add action listeners
        setupEventHandlers();
    }

    private void initializeComponents() {
        darkModeCheckbox = new JCheckBox("Enable Dark Mode");
        notificationsCheckbox = new JCheckBox("Enable Notifications");
        
        String[] timeFormats = {"12-hour format", "24-hour format"};
        timeFormatComboBox = new JComboBox<>(timeFormats);
        
        businessHoursStart = new JTextField(5);
        businessHoursEnd = new JTextField(5);
        
        saveButton = new JButton("Save Settings");
        cancelButton = new JButton("Cancel");
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Appearance Settings:"), gbc);

        // Row 1
        gbc.gridy = 1;
        mainPanel.add(darkModeCheckbox, gbc);

        // Row 2
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Time Format:"), gbc);

        // Row 3
        gbc.gridy = 3;
        mainPanel.add(timeFormatComboBox, gbc);

        // Row 4
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Notification Settings:"), gbc);

        // Row 5
        gbc.gridy = 5;
        mainPanel.add(notificationsCheckbox, gbc);

        // Row 6
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Business Hours (HH:MM):"), gbc);

        // Row 7
        gbc.gridy = 7;
        JPanel hoursPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hoursPanel.add(new JLabel("Start:"));
        hoursPanel.add(businessHoursStart);
        hoursPanel.add(new JLabel("End:"));
        hoursPanel.add(businessHoursEnd);
        mainPanel.add(hoursPanel, gbc);

        // Row 8 - Buttons
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    private void setupEventHandlers() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePreferences();
                JOptionPane.showMessageDialog(SettingsPage.this, 
                    "Settings saved successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadPreferences() {
        darkModeCheckbox.setSelected(prefs.getBoolean("darkMode", false));
        notificationsCheckbox.setSelected(prefs.getBoolean("notifications", true));
        timeFormatComboBox.setSelectedIndex(prefs.getInt("timeFormat", 0));
        businessHoursStart.setText(prefs.get("businessHoursStart", "09:00"));
        businessHoursEnd.setText(prefs.get("businessHoursEnd", "17:00"));
    }

    private void savePreferences() {
        prefs.putBoolean("darkMode", darkModeCheckbox.isSelected());
        prefs.putBoolean("notifications", notificationsCheckbox.isSelected());
        prefs.putInt("timeFormat", timeFormatComboBox.getSelectedIndex());
        prefs.put("businessHoursStart", businessHoursStart.getText());
        prefs.put("businessHoursEnd", businessHoursEnd.getText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SettingsPage().setVisible(true);
            }
        });
    }
}