package com.projectapp.models;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Profile {

    private static JLabel profilePicLabel;
    private static ImageIcon profilePicIcon;
    private static JTextField nameField, emailField, phoneField, facebookField, instagramField, twitterField;
    private static JTextArea bioArea;
    private static String profileFilePath = "profile.json";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Editable Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.setBackground(Color.WHITE);

        JButton changePicButton = new JButton("Change Picture");
        JButton saveButton = new JButton(" Save");
        topRightPanel.add(changePicButton);
        topRightPanel.add(saveButton);

        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        profilePanel.setBackground(Color.WHITE);

        profilePicIcon = loadEmbeddedImage("/IMG_0084.jpg", 100, 100);
        profilePicLabel = new JLabel(new ImageIcon(makeCircularImage(profilePicIcon.getImage())));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setMaximumSize(new Dimension(300, 30));

        JLabel contactLabel = new JLabel("Contact");
        contactLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        emailField.setHorizontalAlignment(SwingConstants.CENTER);
        emailField.setMaximumSize(new Dimension(300, 40));

        phoneField = new JTextField();
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        phoneField.setBorder(BorderFactory.createTitledBorder("Contact Number"));
        phoneField.setHorizontalAlignment(SwingConstants.CENTER); 
        phoneField.setMaximumSize(new Dimension(300, 40));

        JLabel socialsLabel = new JLabel("Social Media");
        socialsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        socialsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        facebookField = new JTextField();
        facebookField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        facebookField.setBorder(BorderFactory.createTitledBorder("Facebook"));
        facebookField.setHorizontalAlignment(SwingConstants.CENTER); 
        facebookField.setMaximumSize(new Dimension(300, 40));

        instagramField = new JTextField();
        instagramField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        instagramField.setBorder(BorderFactory.createTitledBorder("Instagram"));
        instagramField.setHorizontalAlignment(SwingConstants.CENTER);
        instagramField.setMaximumSize(new Dimension(300, 40));

        twitterField = new JTextField();
        twitterField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        twitterField.setBorder(BorderFactory.createTitledBorder("Twitter"));
        twitterField.setHorizontalAlignment(SwingConstants.CENTER);
        twitterField.setMaximumSize(new Dimension(300, 40));


        JLabel bioLabel = new JLabel("Biography");
        bioLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bioArea = new JTextArea();
        bioArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        bioArea.setBackground(new Color(245, 245, 245));
        bioArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        bioArea.setMaximumSize(new Dimension(300, 60)); 

        infoPanel.add(nameLabel);
        infoPanel.add(nameField);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(contactLabel);
        infoPanel.add(emailField);
        infoPanel.add(phoneField);
        infoPanel.add(socialsLabel);
        infoPanel.add(facebookField);
        infoPanel.add(instagramField);
        infoPanel.add(twitterField);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(bioLabel);
        infoPanel.add(bioArea);
        infoPanel.add(Box.createVerticalStrut(5));

        loadProfileData();

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String facebook = facebookField.getText();
            String instagram = instagramField.getText();
            String twitter = twitterField.getText();
            String bio = bioArea.getText();

            saveProfileData(name, email, phone, facebook, instagram, twitter, bio);

            JOptionPane.showMessageDialog(frame,
                "Saved!\n\nName: " + name +
                "\nEmail: " + email +
                "\nPhone: " + phone +
                "\nFacebook: " + facebook +
                "\nInstagram: " + instagram +
                "\nTwitter: " + twitter +
                "\nBio: " + bio,
                "Profile Saved", JOptionPane.INFORMATION_MESSAGE);
        });

        profilePanel.add(profilePicLabel);
        profilePanel.add(infoPanel);

        mainPanel.add(topRightPanel, BorderLayout.NORTH);
        mainPanel.add(profilePanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    private static void loadProfileData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(profileFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("name")) {
                    nameField.setText(line.split(":")[1].trim());
                } else if (line.contains("email")) {
                    emailField.setText(line.split(":")[1].trim());
                } else if (line.contains("phone")) {
                    phoneField.setText(line.split(":")[1].trim());
                } else if (line.contains("facebook")) {
                    facebookField.setText(line.split(":")[1].trim());
                } else if (line.contains("instagram")) {
                    instagramField.setText(line.split(":")[1].trim());
                } else if (line.contains("twitter")) {
                    twitterField.setText(line.split(":")[1].trim());
                } else if (line.contains("bio")) {
                    bioArea.setText(line.split(":")[1].trim());
                }
            }
        } catch (IOException e) {
            // If the file does not exist, create it
            try (FileWriter writer = new FileWriter(profileFilePath)) {
                writer.write("name: \nemail: \nphone: \nfacebook: \ninstagram: \ntwitter: \nbio: ");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void saveProfileData(String name, String email, String phone, String facebook, String instagram, String twitter, String bio) {
        try (FileWriter writer = new FileWriter(profileFilePath)) {
            writer.write("name: " + name + "\n");
            writer.write("email: " + email + "\n");
            writer.write("phone: " + phone + "\n");
            writer.write("facebook: " + facebook + "\n");
            writer.write("instagram: " + instagram + "\n");
            writer.write("twitter: " + twitter + "\n");
            writer.write("bio: " + bio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image makeCircularImage(Image sourceImg) {
        int width = sourceImg.getWidth(null);
        int height = sourceImg.getHeight(null);

        if (width <= 0 || height <= 0)
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

        int size = Math.min(width, height);
        BufferedImage circleImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));
        g2.drawImage(sourceImg, 0, 0, size, size, null);
        g2.dispose();
        return circleImg;
    }

    public static ImageIcon loadEmbeddedImage(String resourcePath, int width, int height) {
        try {
            InputStream stream = Profile.class.getResourceAsStream(resourcePath);
            if (stream == null) throw new Exception("Image not found: " + resourcePath);
            BufferedImage original = ImageIO.read(stream);
            Image scaled = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            System.err.println("⚠️ Failed to load embedded image: " + resourcePath);
            return new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
        }
    }
}
