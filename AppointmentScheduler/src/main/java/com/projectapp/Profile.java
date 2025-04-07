import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProfilePageUI extends JFrame {

    public ProfilePageUI() {
        setTitle("User Profile");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 228, 225));
        mainPanel.setLayout(null); // absolute layout for sketch-style placement

        // Profile Picture
        JLabel profilePic = new JLabel(new ImageIcon(new ImageIcon("profile_placeholder.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        profilePic.setBounds(30, 30, 80, 80);
        mainPanel.add(profilePic);

        // Bio field
        JTextField bioField = new JTextField("This is my bio...");
        bioField.setBounds(130, 40, 300, 25);
        mainPanel.add(bioField);

        // Name label
        JLabel nameLabel = new JLabel("Mary Michael");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setBounds(30, 115, 200, 30);
        mainPanel.add(nameLabel);

        // Appointments section label
        JLabel appointmentLabel = new JLabel("Appointments");
        appointmentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        appointmentLabel.setBounds(30, 160, 200, 30);
        mainPanel.add(appointmentLabel);

        // Appointment entries
        DefaultListModel<String> appointmentList = new DefaultListModel<>();
        appointmentList.addElement("• Dentist - April 10, 2025");
        appointmentList.addElement("• Family Doctor - April 20, 2025");
        appointmentList.addElement("• Vaccination - May 5, 2025");

        JList<String> appointmentJList = new JList<>(appointmentList);
        appointmentJList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(appointmentJList);
        scrollPane.setBounds(30, 200, 420, 150);
        mainPanel.add(scrollPane);

        // Top-right dropdown menu
        JButton menuBtn = new JButton("☰");
        menuBtn.setBounds(440, 10, 40, 30);
        mainPanel.add(menuBtn);

        JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem("Pfp"));
        popup.add(new JMenuItem("Settings"));
        popup.add(new JMenuItem("Schedule"));
        popup.add(new JMenuItem("Log Out"));

        menuBtn.addActionListener((ActionEvent e) -> {
            popup.show(menuBtn, 0, menuBtn.getHeight());
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProfilePageUI::new);
    }
}
