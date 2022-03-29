package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MicroStarServer {
    private static final Logger logger = LogManager.getLogger(MicroStarServer.class);

    public static void main(String[] args) {
        System.out.println("Starting server...");
        new Server();
        //server.start();
    }
}

