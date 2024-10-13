import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Records {

    // Constructor to create the Records UI
    public Records() {
        // Create the main frame
        JFrame frame = new JFrame("RECORDS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);

        // Create a JPanel with a GridLayout (8 rows, 1 column)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10)); // 8 rows, 1 column, 10px gap

        // Create buttons for each category
        JButton contributionButton = new JButton("Contribution");
        JButton exitingButton = new JButton("Exiting");
        JButton profitingButton = new JButton("Profiting");
        JButton membersButton = new JButton("Members");
        JButton membersDividendsButton = new JButton("Members Dividends");
        JButton loansButton = new JButton("Loans");
        JButton loanTypesButton = new JButton("Loan Types");
        JButton loanRepaymentButton = new JButton("Loan Repayment");

        // Add action listeners for each button
        contributionButton.addActionListener(e -> showContributionsOptions());
        exitingButton.addActionListener(e -> showExitingOptions());
        profitingButton.addActionListener(e -> showProfitingOptions());
        membersButton.addActionListener(e -> showMembersOptions());
        membersDividendsButton.addActionListener(e -> showMembersDividendsOptions());
        loansButton.addActionListener(e -> showLoansOptions());
        loanTypesButton.addActionListener(e -> showLoanTypesOptions());
        loanRepaymentButton.addActionListener(e -> showLoanRepaymentOptions());

        // Add buttons to the panel
        panel.add(contributionButton);
        panel.add(exitingButton);
        panel.add(profitingButton);
        panel.add(membersButton);
        panel.add(membersDividendsButton);
        panel.add(loansButton);
        panel.add(loanTypesButton);
        panel.add(loanRepaymentButton);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run the Records UI on its own
        new Records();
    }

    public static void showMembersOptions() {
        // Show Members options
        String query = "SELECT member_id, name, age, registration_fee FROM members";
        fetchDataFromDatabase(query, "Members Details");
    }

    public static void showContributionsOptions() {
        // Show Contributions options
        String query = "SELECT contribution_id, member_id, amount, date FROM contributions";
        fetchDataFromDatabase(query, "Contributions Details");
    }

    public static void showExitingOptions() {
        // Show Exiting options
        String query = "SELECT exiting_id, member_id, exit_date, reason FROM exiting";
        fetchDataFromDatabase(query, "Exiting Details");
    }

    public static void showProfitingOptions() {
        // Show Profiting options
        String query = "SELECT profit_id, amount, date FROM profiting";
        fetchDataFromDatabase(query, "Profiting Details");
    }

    public static void showLoansOptions() {
        // Show Loans options
        String query = "SELECT loan_id, member_id, amount, loan_date FROM loans";
        fetchDataFromDatabase(query, "Loans Details");
    }

    public static void showLoanTypesOptions() {
        // Show Loan Types options
        String query = "SELECT loan_type_id, type_name, interest_rate, repayment_period FROM loan_type";
        fetchDataFromDatabase(query, "Loan Types Details");
    }

    public static void showLoanRepaymentOptions() {
        // Show Loan Repayment options
        String query = "SELECT repayment_id, loan_id, amount_paid, repayment_date FROM loan_repayment";
        fetchDataFromDatabase(query, "Loan Repayment Details");
    }

    public static void showMembersDividendsOptions() {
        // Show Members Dividends options
        String query = "SELECT dividend_id, member_id, amount, dividend_date FROM members_dividends";
        fetchDataFromDatabase(query, "Members Dividends Details");
    }

    // Method to fetch data from the database and populate the table
    public static void fetchDataFromDatabase(String query, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JTable table = new JTable();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fedha_youth_group_db", "root", "ashley24");
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Move to the last row to get the row count
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst(); // Move back to the beginning

            String[][] data = new String[rowCount][columnCount];
            int rowIndex = 0;
            while (rs.next()) {
                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                    data[rowIndex][colIndex - 1] = rs.getString(colIndex);
                }
                rowIndex++;
            }

            // Set the table model
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Add the table to a scroll pane
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}
