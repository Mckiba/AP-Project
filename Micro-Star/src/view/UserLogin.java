package view;

import client.Client;
import model.Customer;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class UserLogin extends JFrame implements ActionListener {


    Container container = getContentPane();
    JLabel userLabel = new JLabel("USER ID");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    JRadioButton jRadioButton1 = new JRadioButton();
    JRadioButton jRadioButton2 = new JRadioButton();
    JRadioButton jRadioButton3 = new JRadioButton();

    ButtonGroup G1 = new ButtonGroup();




    public UserLogin() {
        setLayoutManager();
        setTexts();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        initializeComponent();

    }

    public void setTexts(){
        jRadioButton1.setText("CUSTOMER");
        jRadioButton2.setText("REP");
        jRadioButton3.setText("TECHNICIAN");

    }

    public void initializeComponent() {
        setTitle(" Micro-Star User Login");
        setVisible(true);
        setBounds(10, 10, 600, 700);
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
        loginButton.setBounds(50, 400, 100, 30);
        resetButton.setBounds(200, 400, 100, 30);

        jRadioButton1.setBounds(50, 300, 120, 50);
        jRadioButton2.setBounds(150, 300, 80, 50);
        jRadioButton3.setBounds(250, 300, 80, 50);




    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(jRadioButton1);

        container.add(jRadioButton2);
        container.add(jRadioButton3);

        // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
        G1.add(jRadioButton1);
        G1.add(jRadioButton2);
        G1.add(jRadioButton3);

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
            String type = "";
            if (jRadioButton1.isSelected()) {
                type = "CUSTOMER";
            }
            else if (jRadioButton2.isSelected()) {
                type = "REP";
            } else if (jRadioButton3.isSelected()){
                type = "TECHNICIAN";
            }


            try{
                //TODO: TAKE THIS OPERATION SOMEWHERE ELSE
                //TODO: ENSURE A RADIO BUTTON IS SELECTED

                Client client = new Client();
                User user = new User(pwdText,userText,type);
                String operation = "AUTHENTICATE";
                client.sendAction(operation);
                System.out.println("MESSAGE SENT TO SERVER");
                client.sendUser(user);
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

