package com.projectapp.services;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean loginSuccessful = false;

    public LoginDialog(JDialog parent) {
        super(parent, "Log In", true);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Log In");
        add(loginButton);
        loginButton.addActionListener(e -> loginUser ());

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void loginUser () {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (validateUser (username, password)) {
            loginSuccessful = true;
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private boolean validateUser (String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(username) && line.contains(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}