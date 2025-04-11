package com.projectapp.services;

import javax.swing.*;

import com.projectapp.Dashboard;

import java.awt.*;

public class AuthDialog extends JDialog {
    private boolean loginSuccessful = false;

    public AuthDialog(JFrame parent) {
        super(parent, "Authentication", true);
        setLayout(new GridLayout(2, 1));

        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Log In");

        signUpButton.addActionListener(e -> {
            new SignUpDialog(this);
        });

        loginButton.addActionListener(e -> {
            LoginDialog loginDialog = new LoginDialog(this);
            if (loginDialog.isLoginSuccessful()) {
                loginSuccessful = true;
                dispose();
                SwingUtilities.invokeLater(() -> new Dashboard().createUI());
            }
        });

        add(signUpButton);
        add(loginButton);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}