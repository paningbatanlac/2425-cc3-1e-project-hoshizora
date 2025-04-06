package com.projectapp;

import javax.swing.*;

import com.projectapp.services.AuthDialog;

public class Main {
    public static void main(String[] args) {
        AuthDialog authDialog = new AuthDialog(null);
        
        if (authDialog.isLoginSuccessful()) {
            SwingUtilities.invokeLater(() -> new AppointmentScheduler());
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting application.");
            System.exit(0);
        }
    }
}