package server;

import model.User;
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
    ObjectOutputStream dos;
    ObjectInputStream dis;
    User user;


    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }


    @Override
    public void run() {
        while (true) {


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
                                    System.out.println(user.getEmail());
                                    //TODO: HANDLE LOGIN

                                    boolean userAuthenticated = UserOperation.loginAuth(user);
                                    if (userAuthenticated) {
                                        System.out.println("USER AUTHENTICATED" + userAuthenticated);
                                        dos.writeObject(userAuthenticated);
                                    }
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

