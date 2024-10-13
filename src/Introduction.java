import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Introduction {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Introduction To Fedha Youth Group");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a main panel with GridLayout (2 rows, 2 columns)
        JPanel gridPanel = new JPanel(new GridLayout(2, 2)); // 2 rows, 2 columns

        // Create buttons for Forms, Reports, and Records
        JButton formsButton = new JButton("Forms");
        JButton reportsButton = new JButton("Reports");
        JButton recordsButton = new JButton("Records");

        // Add action listeners to buttons
        formsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Forms(); // Create an instance of the Forms class
            }
        });

        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Reports Button Clicked");
            }
        });

        recordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Records(); // Create an instance of the Records class
            }
        });

        // Add buttons to the grid panel
        gridPanel.add(formsButton);
        gridPanel.add(reportsButton);
        gridPanel.add(recordsButton);
        gridPanel.add(new JButton("")); // Empty cell to keep 2x2 layout

        // Add the grid panel to the frame
        frame.add(gridPanel);

        // Set frame visibility
        frame.setVisible(true);
    }
}
