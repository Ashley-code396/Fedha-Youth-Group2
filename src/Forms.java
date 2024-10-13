import javax.swing.*;
import java.awt.*;

public class Forms {
    public static void main(String[] args) {new Forms();}

    public Forms() {
        // Create the main frame
        JFrame frame = new JFrame("Forms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a JPanel for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 20, 20)); // Grid layout for buttons (2 rows, 2 columns, 20px gaps)

        // Create standard rectangular buttons
        JButton memberRegistrationButton = new JButton("Member Registration");
        JButton applyLoanButton = new JButton("Apply Loan");
        JButton applyExitButton = new JButton("Apply Exit");
        JButton computeDividendsButton = new JButton("Compute Dividends");

        // Add action listeners to buttons
        memberRegistrationButton.addActionListener(e -> new MemberRegistrationForm()); // Open MemberRegistrationForm

        applyLoanButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Apply Loan clicked"));
        applyExitButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Apply Exit clicked"));
        computeDividendsButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Compute Dividends clicked"));

        // Add the buttons to the panel
        panel.add(memberRegistrationButton);
        panel.add(applyLoanButton);
        panel.add(applyExitButton);
        panel.add(computeDividendsButton);

        // Add the panel to the frame
        frame.add(panel);

        // Set frame visibility
        frame.setVisible(true);
    }
}
