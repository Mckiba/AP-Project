package server;

import model.Accounts;
import model.Complaints;
import model.User;
import services.AccountOperations;
import services.ComplaintOperations;
import services.UserOperation;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


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
                                    System.out.println("LOG OFF");
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
                                case "GET-COMPLAINTS-SHAKERA" -> {
                                    User user = (User) operand;
                                    System.out.println("GET COMPLAINT");
                                    List<Complaints> complaintRecorded = ComplaintOperations.queryComplaints(user);
                                    Oos.writeObject(true);
                                    Oos.writeObject(complaintRecorded);
                                }
                                case "ACCOUNT-QUERY" -> {
                                    User user = (User) operand;
                                    System.out.println("QUERY");
                                    System.out.println(user.getCustomerID());
                                    Accounts account = AccountOperations.queryAccount(user);
                                    Oos.writeObject(true);
                                    Oos.writeObject(account);
                                }
                                case "GET-COMPLAINTS" -> {
                                    User user = (User) operand;
                                    System.out.println("GET COMPLAINTS IN A LIST ");
                                    ArrayList<Complaints> complaints = ComplaintOperations.getAllComplaints();
                                    Oos.writeObject(true);
                                    Oos.writeObject(complaints);
                                    System.out.println("COMPLAINTS" + complaints.toString());
                                }
                                case "FILTER-COMPLAINTS" -> {
                                    String issueID = (String) operand;
                                    Complaints complaint = ComplaintOperations.getComplaint(issueID);
                                    Oos.writeObject(true);
                                    Oos.writeObject(complaint);
                                    System.out.println("FILTER COMPLAINTS" + complaint.toString());
                                }
                                case "ADD-RESPONSE" -> {
                                    ArrayList<Object>objects = (ArrayList<Object>) operand;

                                    String issueID = (String) objects.get(0).toString();
                                    String userID = (String) objects.get(1).toString();
                                    String response = (String) objects.get(2).toString();
                                    boolean complaintAdded = ComplaintOperations.addResponse(issueID,userID,response);
                                    Oos.writeObject(complaintAdded);
                                    //Oos.writeObject(complaint);
                                    System.out.println("ADDED RESPONSE");
                                }
                                case "ASSIGN-TECHNICIAN" -> {
                                    ArrayList<Object>objects = (ArrayList<Object>) operand;

                                    String issueID = (String) objects.get(0).toString();
                                    String techID = (String) objects.get(1).toString();
                                    boolean techAssigned = ComplaintOperations.assignTechnician(issueID,techID);
                                    Oos.writeObject(techAssigned);
                                    //Oos.writeObject(complaint);
                                    System.out.println("ASSIGNED TECH");
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
