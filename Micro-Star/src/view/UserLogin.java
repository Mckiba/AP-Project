package view;

import client.Client;
import model.Customer;

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
    JRadioButton customer_rdbtn = new JRadioButton("Student");
    JRadioButton technician_rdbtn = new JRadioButton("Agent");
    JRadioButton rep_rdbtn = new JRadioButton("Rep");


    UserLogin() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        initializeComponent();
    }

    public void initializeComponent() {
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
        customer_rdbtn.setBounds(50, 340, 20, 20);
        technician_rdbtn.setBounds(100, 340, 20, 20);
        rep_rdbtn.setBounds(150, 340, 20, 20);

    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(customer_rdbtn);
        container.add(technician_rdbtn);
        container.add(rep_rdbtn);
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

            try{

                Client client = new Client();
                Customer customer = new Customer(userText,pwdText);


                client.sendAction("AUTHENTICATE");
                System.out.println("MESSAGE SENT TO SERVER");

                client.sendUser(customer);
                System.out.println("RECORD SENT TO SERVER");

                client.receiveResponse();
                System.out.println("RESPONSE RECEIVED FROM SERVER");


            }catch (Exception ex){
                ex.printStackTrace();
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

