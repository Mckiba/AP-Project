package server;

import model.User;

import java.io.*;
import java.net.Socket;
import java.sql.*;


public class ClientHandler implements Runnable {

        Socket clientSocket;
        ObjectOutputStream dos;
        ObjectInputStream dis;
        User user = null;
    private static Connection dBConn = null;


    public ClientHandler(Socket socket) {
            this.clientSocket=socket;
        }


        @Override
        public void run() {
            while (true) {
                double radius, area;
                try {
                    dos = new ObjectOutputStream(clientSocket.getOutputStream());
                    dis = new ObjectInputStream(clientSocket.getInputStream());

                    String action = "";
                    //getDatabaseConnection();
                    try {
                        while (true) {
                            {
                                action = (String) dis.readObject();
                                user = (User) (dis.readObject());
                                switch (action) {
                                    case "AUTHENTICATE":
                                        System.out.println("AUTHENTICATE");//handleLogin((User) receivedOp.get(1));
                                        System.out.println(user);
                                        dos.writeObject(true);
                                        //TODO: HANDLE LOGIN
                                        break;

                                    case "LOG-OFF":
                                        //TODO:HANDLE LOGOFF
                                        System.out.println("LOG OFF");//handleLogOff();
                                        break;
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

