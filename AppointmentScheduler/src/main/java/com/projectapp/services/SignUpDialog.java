package com.projectapp.services;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import com.projectapp.models.User;

public class SignUpDialog extends JDialog {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;

    public SignUpDialog(JDialog parent) {
        super(parent, "Sign Up", true);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);

        JButton signUpButton = new JButton("Sign Up");
        add(signUpButton);
        signUpButton.addActionListener(e -> registerUser ());

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void registerUser () {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (password.equals(confirmPassword) && !username.isEmpty() && !email.isEmpty()) {
            User newUser  = new User(username, username, email, "", "Client", password);
            saveUser (newUser );
            JOptionPane.showMessageDialog(this, "Registration successful!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Passwords do not match or fields are empty.");
        }
    }

    private void saveUser (User user) {
        try (FileWriter file = new FileWriter("users.json", true)) {
            String userJson = String.format("{\"userID\":\"%s\",\"name\":\"%s\",\"email\":\"%s\",\"password\":\"%s\"}\n",
                    user.getUserID(), user.getName(), user.getEmail(), user.getPassword());
            file.write(userJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}