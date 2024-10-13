import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberRegistrationForm {

    public static void main(String[] args) {new MemberRegistrationForm();}

    public MemberRegistrationForm() {
        // Create JFrame for the registration form
        JFrame frame = new JFrame("New Member Registration");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close just the registration form
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create a JPanel for the form
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use absolute layout
        frame.add(panel);

        // Add form fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150, 50, 165, 25);
        panel.add(nameText);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 90, 80, 25);
        panel.add(ageLabel);

        JTextField ageText = new JTextField(20);
        ageText.setBounds(150, 90, 165, 25);
        panel.add(ageText);

        JLabel registrationFeeLabel = new JLabel("Registration Fee:");
        registrationFeeLabel.setBounds(50, 130, 120, 25);
        panel.add(registrationFeeLabel);

        JTextField registrationFeeText = new JTextField(20);
        registrationFeeText.setBounds(150, 130, 165, 25);
        panel.add(registrationFeeText);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 200, 100, 30);
        panel.add(submitButton);

        // Action Listener for the Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String name = nameText.getText();
                String age = ageText.getText();
                String registrationFee = registrationFeeText.getText();

                // Insert the data into the database
                insertMemberData(name, age, registrationFee);
            }
        });

        // Set the frame visibility
        frame.setVisible(true);
    }

    // Method to insert the member data into the database
    private static void insertMemberData(String name, String age, String registrationFee) {
        String url = "jdbc:mysql://localhost:3306/fedha_youth_group_db"; // Database URL
        String user = "root"; // Database username
        String password = "ashley24"; // Database password

        String query = "INSERT INTO members (name, age, registration_fee) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the values in the SQL query
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(age));
            pstmt.setDouble(3, Double.parseDouble(registrationFee));

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Member registered successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to register member.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }
    }
}
