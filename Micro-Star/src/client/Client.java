package client;

import model.Accounts;
import model.Complaints;
import model.User;
import view.AccountQuery;
import view.Dashboard;
import view.UserLogin;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    static UserLogin userLogin;
    static Dashboard dashboard;

    public static void main(String[] args) throws IOException {
        userLogin = new UserLogin();

        User user = new User("qwe","123","test");
        dashboard = new Dashboard(user);
    }
    private ObjectOutputStream objOs;
    private ObjectInputStream obIs;
    private String action;
    private Socket connectionSocket;

    public Client() throws IOException {
        this.createConnection();
        this.configureStreams();
    }

    private void createConnection() {
        try {
            connectionSocket = new Socket("127.0.0.1", 8888);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureStreams() throws IOException {
        obIs = new ObjectInputStream(connectionSocket.getInputStream());
        try {
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            objOs.close();
            obIs.close();
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAction(String action) {
        this.action = action;
        try {
            objOs.writeObject(action);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendComplaint(Complaints complaintObj) {
        this.action = action;
        try {
            objOs.writeObject(complaintObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUser(User userObj) {
        this.action = action;
        try {
            objOs.writeObject(userObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAccount(Accounts accountsObj) {
        this.action = action;
        try {
            objOs.writeObject(accountsObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveResponse() {
        try {
            if (action.equalsIgnoreCase("AUTHENTICATE")) {
                Boolean flag = (Boolean) obIs.readObject();
                User user = (User) obIs.readObject();

                if (flag) {
                    JOptionPane.showMessageDialog(null, "LOGIN successfully",
                            "LOGIN", JOptionPane.INFORMATION_MESSAGE);
                            userLogin.setVisible(false);
                            new Dashboard(user);
                }else{
                    JOptionPane.showMessageDialog(null, "LOGIN FAILED",
                            "LOGIN", JOptionPane.INFORMATION_MESSAGE);
                }
                if (action.equalsIgnoreCase("LOG OFF")) {
                    System.out.println("LOGGING OGG");
                }
            }
            if (action.equalsIgnoreCase("ADD-COMPLAINT")) {
                Boolean flag = (Boolean) obIs.readObject();
                if (flag) {
                    JOptionPane.showMessageDialog(null, "COMPLAINT RECORDED",
                            "COMPLAINT", JOptionPane.INFORMATION_MESSAGE);
                    userLogin.setVisible(false);
                   }else{
                    JOptionPane.showMessageDialog(null, "FAILED TO ADD COMPLAINT",
                            "COMPLAINT", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (action.equalsIgnoreCase("GET-COMPLAINTS")) {
                Boolean flag = (Boolean) obIs.readObject();
                List<Complaints> complaintsList = (List<Complaints>) obIs.readObject();

                if (flag) {

//                    AccountQuery accountQuery = new AccountQuery(accounts);
//                    accountQuery.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "FAILED TO GET Complaints",
                            "COMPLAINT", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (action.equalsIgnoreCase("ACCOUNT-QUERY")) {
                Boolean flag = (Boolean) obIs.readObject();
                Accounts accounts = (Accounts) obIs.readObject();

                if (flag) {

                    AccountQuery accountQuery = new AccountQuery(accounts);
                    accountQuery.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "FAILED TO GET ACCOUNT DETAILS",
                            "COMPLAINT", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (ClassCastException | ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public Object doOperation(ArrayList<Object> operand) {
        Object result = null;

        try {
            objOs.writeObject(operand);
            result = obIs.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


}










