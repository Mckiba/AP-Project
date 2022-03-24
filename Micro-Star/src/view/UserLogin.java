package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserLogin extends JFrame implements ActionListener {


    public static void main(String[] args) {
        new UserLogin();
    }
    Container container = getContentPane();
    JLabel userLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    UserLogin() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        initializeComponent();
    }

    public void initializeComponent(){
        setTitle(" Micro-Star User Login");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());

            //TODO: SELECT FROM CUSTOMER TABLE WHERE USERNAME AND PASSWORD MATCH
            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
                        "root", "root");

                PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select email, password from Customer where name=? and password=?");

                st.setString(1, userText);
                st.setString(2, pwdText);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    System.out.println("LOGGED IN");
                    //dispose();
                    //UserHome ah = new UserHome(userName);
                    //ah.setTitle("Welcome");
                    //ah.setVisible(true);
                    //JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                } else {
                    System.out.println("PASSWORD FAILED");
                    //JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}

