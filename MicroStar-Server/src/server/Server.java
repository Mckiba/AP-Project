package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// Server class
public class Server {


    public Server() {
        System.out.println("RUNNING");

        ServerSocket server = null;
        try {
            // server is listening on port 8888
            server = new ServerSocket(8888);
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {
                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                        + client.getInetAddress()
                        .getHostAddress());
                int clientCount = 0;
                System.out.println("Server starting a thread for client" + clientCount);
                System.out.println("Server has started at:" + new Date() + "\n");
                System.out.println("CLIENT COUNT" + clientCount);

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

