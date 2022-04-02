package server;

import model.Complaints;
import model.User;
import services.ComplaintOperations;
import services.UserOperation;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;


public class ClientHandler implements Runnable {

    private static Connection dBConn = null;
    Socket clientSocket;
    ObjectOutputStream Oos;
    ObjectInputStream Ois;
    User user;


    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }


    @Override
    public void run() {
        while (true) {

            try {
                Oos = new ObjectOutputStream(clientSocket.getOutputStream());
                Ois = new ObjectInputStream(clientSocket.getInputStream());

                String action = "";
                //getDatabaseConnection();
                try {
                    while (true) {
                        {
                            action = (String) Ois.readObject();
                            Object operand = Ois.readObject();

                            switch (action) {
                                case "AUTHENTICATE" -> {
                                    User user = (User) operand;
                                    System.out.println("AUTHENTICATE");//handleLogin((User) receivedOp.get(1));
                                    System.out.println(user.getEmail());
                                    boolean userAuthenticated = UserOperation.loginAuth(user);
                                    if (userAuthenticated) {
                                        System.out.println("USER AUTHENTICATED" + true);
                                        Oos.writeObject(true);
                                        Oos.writeObject(user);
                                    } else Oos.writeObject(false);
                                }
                                case "LOG-OFF" -> {
                                    //TODO:HANDLE LOGOFF
                                    System.out.println("LOG OFF");//handleLogOff();
                                }

                                case "ADD-COMPLAINT" -> {
                                    Complaints complaint = (Complaints) operand;
                                    System.out.println("ADD COMPLAINT");
                                    System.out.println("ID: "+ complaint.getCustomerID() +" COMPLAINT: "+ complaint.getIssueDetails());
                                    boolean complaintRecorded = ComplaintOperations.addComplaint(complaint);
                                    if(complaintRecorded){
                                        System.out.println("COMPLAINT RECORDED" + true);
                                        Oos.writeObject(true);
                                    }else Oos.writeObject(false);
                                }

                            }
                        }
                    }
                } catch (EOFException ex) {
                    System.out.println("Client has terminated connections with the server");
                    ex.printStackTrace();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

