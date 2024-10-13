import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {
    // Login credentials
    private static final String USERNAME = "Ashley";
    private static final String PASSWORD = "ashley24";

    // Declare JFrame at the class level
    private static JFrame frame;

    public static void main(String[] args) {
        // Create JFrame
        frame = new JFrame("FEDHA YOUTH GROUP"); // Initialize the frame
        frame.setSize(550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Set frame visibility
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Set background color
        panel.setBackground(Color.lightGray); // You can choose any colour of your choice here

        // Username Label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(130, 50, 80, 25);
        panel.add(userLabel);

        // Username Text Field
        JTextField userText = new JTextField(20);
        userText.setBounds(210, 50, 165, 25);
        panel.add(userText);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(130, 90, 80, 25);
        panel.add(passwordLabel);

        // Password Field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(210, 90, 165, 25);
        panel.add(passwordText);

        // Submit Button
        JButton loginButton = new JButton("Submit");
        loginButton.setBounds(245, 170, 80, 25);
        panel.add(loginButton);

        // Action Listener for Submit Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                // Check credentials
                if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                    JOptionPane.showMessageDialog(panel, "Login Successful!");
                    frame.dispose(); // Close the login window after successful login
                    Introduction.main(new String[0]); // Call the main method of records class
                } else {
                    JOptionPane.showMessageDialog(panel, "Incorrect Username/Password.");
                }
            }
        });
    }
}
