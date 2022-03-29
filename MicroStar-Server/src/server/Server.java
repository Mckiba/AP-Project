package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import javax.swing.*;



public class Server extends Thread{
    private static Connection dBConn = null;
    private Statement stmt;
    private ResultSet result = null;
    private ServerSocket serverSocket;
    private static Socket connectionSocket;
    private static ObjectInputStream objIs;
    private static ObjectOutputStream objOs;

    Server(){
        this.createConnection();
        this.waitForRequests();
    }

    private void createConnection() {
        try {
            //Create new instance of the server-socket listening on port 8888
            serverSocket = new ServerSocket(8888);
        }catch ( IOException ex){
                ex.printStackTrace();
        }
    }



    private void configureStreams() {
        try {
            //Instantiate the output stream, using the getOutputStream method
            //of the Socket object as argument to the constructor
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            //Instantiate the input stream, using the getOutputStream method
            //of the Socket object as argument to the constructor
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Connection getDatabaseConnection(){
            if (dBConn == null) {
                try {
                    String url = "jdbc:mysql://localhost:3306/MicroStar";
                    dBConn = DriverManager.getConnection(url, "root", "");
                    JOptionPane.showMessageDialog(null, "B Connection Established", "CONNECTION STATUS", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Could not connect to database\n" + ex, "Connection Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
        return dBConn;
    }


    static void closeConnection() {
        try {
        objOs.close();
        objIs.close();
        connectionSocket.close();
    }catch(IOException ex){
        ex.printStackTrace();
    }
    }


    private void waitForRequests() {
        String action = "";
        getDatabaseConnection();
        try {
            while (true) {
            connectionSocket = serverSocket.accept ();
            this.configureStreams();
            try
            {
                action = (String)objIs.readObject();
                switch(action) {
                    case "AUTHENTICATE":
                        System.out.println("AUTHENTICATE");//handleLogin((User) receivedOp.get(1));
                        //TODO: HANDLE LOGIN
                        break;

                    case "LOG-OFF":
                        //TODO:HANDLE LOGOFF
                        System.out.println("LOG OFF");//handleLogOff();
                        break;
                }
            } catch (ClassNotFoundException | ClassCastException ex) {
                ex.printStackTrace();
            }
                this.closeConnection();
            }
            } catch (EOFException ex){
            System.out.println("Client has terminated connections with the server");
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
