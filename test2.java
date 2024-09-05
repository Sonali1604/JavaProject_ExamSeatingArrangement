package swapy;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class test2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtStudentName, txtRollNo, txtBatch;
    private JButton btnConfirmSeat, btnCancelSeat, btnView, btnBatches, btnSeatingSituation;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public test2() {
        initLoginFrame();
    }

    private void initLoginFrame() {
        JFrame loginFrame = new JFrame("Admin Login");
        loginFrame.setVisible(true);
        loginFrame.setSize(640, 480);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        loginFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(128, 128, 0), null, new Color(184, 134, 11), null));
        panel.setForeground(new Color(0, 0, 205));

        JLabel lblAdminID = new JLabel("AdminID:");
        lblAdminID.setBounds(20, 49, 96, 51);
        lblAdminID.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAdminID.setForeground(new Color(139, 0, 0));
        lblAdminID.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField txtAdminID = new JTextField();
        txtAdminID.setBounds(48, 110, 165, 40);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(new Color(128, 0, 0));
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(20, 186, 108, 49);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(48, 245, 165, 40);

        JButton btnSubmit = new JButton("LOGIN");
        btnSubmit.setForeground(new Color(0, 0, 0));
        btnSubmit.setBackground(new Color(175, 238, 238));
        btnSubmit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        btnSubmit.setBounds(100, 331, 108, 40);

        panel.setLayout(null);

        panel.add(lblAdminID);
        panel.add(txtAdminID);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnSubmit);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\swapnil\\Desktop\\Rit (1).png"));
        lblNewLabel.setBounds(0, 0, 626, 443);
        panel.add(lblNewLabel);

        loginFrame.getContentPane().add(panel);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminID = txtAdminID.getText();
                char[] password = txtPassword.getPassword();
                String enteredPassword = new String(password);

                if (isValidLogin(adminID, enteredPassword)) {
                    JOptionPane.showMessageDialog(loginFrame, "WELCOME " + adminID);
                    loginFrame.dispose();
                    initializeMainApplication();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "INVALID LOGIN");
                }
            }
        });
    }
    
    private boolean isValidLogin(String adminID, String password) {
        return adminID.equals("swapy") && password.equals("0712");
    }

    
    private void initializeMainApplication() {
        setTitle("Exam Seating Arrangement");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        addListeners();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblRollNo = new JLabel("Roll Number:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblRollNo, gbc);

        txtRollNo = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtRollNo, gbc);

        JLabel lblStudentName = new JLabel("Student Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblStudentName, gbc);

        txtStudentName = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtStudentName, gbc);

        JLabel lblBatch = new JLabel("Batch:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblBatch, gbc);

        txtBatch = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtBatch, gbc);

        btnConfirmSeat = new JButton("Confirm Seat");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnConfirmSeat, gbc);

        btnCancelSeat = new JButton("Cancel Seat");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(btnCancelSeat, gbc);

        btnView = new JButton("View Seating Arrangement");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(btnView, gbc);

        btnBatches = new JButton("Batches");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(btnBatches, gbc);

        btnSeatingSituation = new JButton("Show Seating Situation");
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(btnSeatingSituation, gbc);

        getContentPane().add(panel);
    }
    private void addListeners() {
        btnConfirmSeat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmSeat();
            }
        });

        btnCancelSeat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelSeat();
            }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAssignedSeats();
            }
        });

        btnBatches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBatches();
            }
        });

        btnSeatingSituation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSeatingSituation();
            }
        });
        
    }

    private String getNextAvailableSeat(Connection connection, String classroom) throws SQLException {
        char classroomChar = classroom.charAt(0);
        for (int row = 1; row <= 12; row += 2) {
            String seatNumber = classroomChar + "R" + row;

            if (!seatExists(connection, seatNumber)) {
                return seatNumber;
            }
        }

        int nextRow = 2; // Starting with the second row
        String seatNumber = classroomChar + "R" + nextRow;

        // Finding available seat in the next row
        while (seatExists(connection, seatNumber)) {
            nextRow += 2; // Moving to alternate row
            seatNumber = classroomChar + "R" + nextRow;
        }

        return seatNumber;
    }
    
    private String assignSeat(Connection connection, String studentBatch, String rollNo, String studentName) throws SQLException {
        String classroom = getClassroomForBatch(studentBatch);
        String seatNumber = getNextAvailableSeat(connection, classroom);

        if (seatExists(connection, seatNumber)) {
            updateSeatAssignment(connection, rollNo, studentName, seatNumber);
        } else {
            insertSeatAssignment(connection, rollNo, studentName, studentBatch, seatNumber);
        }

        return seatNumber;
    }							

    private String getClassroomForBatch(String studentBatch) {
        String[] classroomsFE = {"A", "B"};
        String[] classroomsSYFY = {"C", "D"};

        if ("FE".equals(studentBatch) || "TY".equals(studentBatch)) {
            return getRandomElement(classroomsFE);
        } else if ("SY".equals(studentBatch) || "FY".equals(studentBatch)) {
            return getRandomElement(classroomsSYFY);
        } else {
            return getRandomClassroom();
        }
    }

    private String getRandomClassroom() {
        String[] allClassrooms = {"A", "B", "C", "D"};
        return getRandomElement(allClassrooms);
    }

    private String getRandomElement(String[] array) {
        int randomIndex = (int) (Math.random() * array.length);
        return array[randomIndex];
    }


    private boolean seatExists(Connection connection, String seatNumber) throws SQLException {
        String query = "SELECT COUNT(*) FROM seating_arrangement WHERE seat_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, seatNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count > 0;
    }
    
    private void updateSeatAssignment(Connection connection, String rollNo, String studentName, String seatNumber) throws SQLException {
        String updateQuery = "UPDATE seating_arrangement SET roll_no = ?, student_name = ? WHERE seat_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, rollNo);
        preparedStatement.setString(2, studentName);
        preparedStatement.setString(3, seatNumber);
        preparedStatement.executeUpdate();
    }

    private void viewAssignedSeats() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String query = "SELECT roll_no, student_name, class, seat_number FROM seating_arrangement";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Roll No.");
            tableModel.addColumn("Name");
            tableModel.addColumn("Class");
            tableModel.addColumn("Seat Number");

            while (resultSet.next()) {
                String rollNo = resultSet.getString("roll_no");
                String studentName = resultSet.getString("student_name");
                String studentClass = resultSet.getString("class");
                String seatNumber = resultSet.getString("seat_number");

                tableModel.addRow(new Object[]{rollNo, studentName, studentClass, seatNumber});
            }

            JFrame assignedSeatsFrame = new JFrame("Assigned Seats");
            assignedSeatsFrame.setSize(600, 400);
            assignedSeatsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            assignedSeatsFrame.setLocationRelativeTo(null);

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            assignedSeatsFrame.getContentPane().add(scrollPane);

            assignedSeatsFrame.setVisible(true);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private JScrollPane createBatchTable(Connection connection, String batch) {
        try {
            String query = "SELECT roll_no, student_name FROM csitlist WHERE class = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, batch);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Roll No.");
            tableModel.addColumn("Name");

            while (resultSet.next()) {
                String rollNo = resultSet.getString("roll_no");
                String studentName = resultSet.getString("student_name");

                tableModel.addRow(new Object[]{rollNo, studentName});
            }

            JTable table = new JTable(tableModel);
            return new JScrollPane(table);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private JScrollPane createSeatingSituationTable(Connection connection, String classroom) {
        try {
            String query = "SELECT roll_no, student_name, seat_number FROM seating_arrangement WHERE seat_number LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classroom + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Roll No.");
            tableModel.addColumn("Name");
            tableModel.addColumn("Seat Number");

            while (resultSet.next()) {
                String rollNo = resultSet.getString("roll_no");
                String studentName = resultSet.getString("student_name");
                String seatNumber = resultSet.getString("seat_number");

                tableModel.addRow(new Object[]{rollNo, studentName, seatNumber});
            }

            JTable table = new JTable(tableModel);
            return new JScrollPane(table);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void confirmSeat() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String rollNo = txtRollNo.getText();
            String studentName = txtStudentName.getText();
            String studentBatch = txtBatch.getText();

            String seatNumber = assignSeat(connection, studentBatch, rollNo, studentName);

            insertSeatAssignment(connection, rollNo, studentName, studentBatch, seatNumber);

            insertCsitList(connection, rollNo, studentName, studentBatch);

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cancelSeat() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String rollNo = txtRollNo.getText();
            deleteSeatAssignment(connection, rollNo);

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertSeatAssignment(Connection connection, String rollNo, String studentName, String studentBatch, String seatNumber) {
        try {
            String query = "INSERT INTO seating_arrangement (roll_no, student_name, class, seat_number) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollNo);
            preparedStatement.setString(2, studentName);
            preparedStatement.setString(3, studentBatch);
            preparedStatement.setString(4, seatNumber);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertCsitList(Connection connection, String rollNo, String studentName, String studentBatch) {
        try {
            String query = "INSERT INTO csitlist (roll_no, student_name, class) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollNo);
            preparedStatement.setString(2, studentName);
            preparedStatement.setString(3, studentBatch);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteSeatAssignment(Connection connection, String rollNo) {
        try {
            String query = "DELETE FROM seating_arrangement WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollNo);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showBatches() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String batch = JOptionPane.showInputDialog("Enter Batch (FE, SY, TY, FY):");

            if (batch != null && (batch.equals("FE") || batch.equals("SY") || batch.equals("TY") || batch.equals("FY"))) {
                JScrollPane batchTable = createBatchTable(connection, batch);
                JFrame batchFrame = new JFrame("Batch " + batch);
                batchFrame.setSize(400, 300);
                batchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                batchFrame.setLocationRelativeTo(null);
                batchFrame.getContentPane().add(batchTable);

                batchFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Batch");
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showSeatingSituation() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String classroom = JOptionPane.showInputDialog("Enter Classroom (A, B, C, D):");

            if (classroom != null && (classroom.equals("A") || classroom.equals("B") || classroom.equals("C") || classroom.equals("D"))) {
                JScrollPane seatingTable = createSeatingSituationTable(connection, classroom);
                JFrame seatingFrame = new JFrame("Seating Situation in Classroom " + classroom);
                seatingFrame.setSize(600, 400);
                seatingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                seatingFrame.setLocationRelativeTo(null);
                seatingFrame.getContentPane().add(seatingTable);

                seatingFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Classroom");
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new test2();
            }
        });
    }
}
