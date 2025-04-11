package com.projectapp.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class SettingsPage extends JFrame {
    private JCheckBox darkModeCheckbox;
    private JCheckBox notificationsCheckbox;
    private JComboBox<String> timeFormatComboBox;
    private JTextField businessHoursStart;
    private JTextField businessHoursEnd;
    private JButton saveButton;
    private JButton cancelButton;

    private Preferences prefs = Preferences.userNodeForPackage(SettingsPage.class);

    public SettingsPage() {
        setTitle("Appointment Scheduler - Settings");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
        loadPreferences();
        setupLayout();
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Appearance Settings:"), gbc);

        gbc.gridy = 1;
        mainPanel.add(darkModeCheckbox, gbc);

        gbc.gridy = 2;
        mainPanel.add(new JLabel("Time Format:"), gbc);

        gbc.gridy = 3;
        mainPanel.add(timeFormatComboBox, gbc);

        gbc.gridy = 4;
        mainPanel.add(new JLabel("Notification Settings:"), gbc);

        gbc.gridy = 5;
        mainPanel.add(notificationsCheckbox, gbc);

        gbc.gridy = 6;
        mainPanel.add(new JLabel("Business Hours (HH:MM):"), gbc);

        gbc.gridy = 7;
        JPanel hoursPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hoursPanel.add(new JLabel("Start:"));
        hoursPanel.add(businessHoursStart);
        hoursPanel.add(new JLabel("End:"));
        hoursPanel.add(businessHoursEnd);
        mainPanel.add(hoursPanel, gbc);

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