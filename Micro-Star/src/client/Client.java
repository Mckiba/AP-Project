package client;

import model.Customer;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

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

    public void sendUser(Customer userObj) {
        this.action = action;
        try {
            objOs.writeObject(userObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveResponse() {
        try {
            if (action.equalsIgnoreCase("AUTHENTICATE")) {
                Boolean flag = (Boolean) obIs.readObject();
                if (flag) {
                    JOptionPane.showMessageDialog(null, "LOGIN successsfully",
                            "LOGIN", JOptionPane.INFORMATION_MESSAGE);
                }
                if (action.equalsIgnoreCase("LOG OFF")) {
                    System.out.println("LOGGING OGG");
                }
            }
        } catch (ClassCastException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        }
    }
}











