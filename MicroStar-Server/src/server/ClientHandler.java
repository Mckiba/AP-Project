package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;

import com.model.User;
import services.LoginAuth;

public class ClientHandler extends Thread{
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    private final Server server;
    private final Socket socketConnection;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private User account = null;

    public ClientHandler(Server server, Socket socket) {
        logger.info("Socket connection recieved by server thread");
        this.server = server;
        this.socketConnection = socket;
    }

    //Gets current user logged in using the connection
    public User getAccount() {
        return account;
    }

    @Override
    public void run() {

        logger.info("Executing server thread");
        try{
            os = new ObjectOutputStream(socketConnection.getOutputStream());
            is = new ObjectInputStream(socketConnection.getInputStream());

            while(!socketConnection.isClosed()) {
                logger.info("Request recieved from CLIENT");
                Object operand = is.readObject();

                /**
                 * If a LOGGING EVENT was sent to the server then designate and LOG.
                 */
                if(operand instanceof LogEvent)
                    logEvent(operand);
                else
                    doOperation(operand);

                os.flush();
            }
        }catch(IOException ioex){
            logger.error("ERROR COMMUNICATING USING I/O - " + ioex.getMessage()
                    + "\nAT: " + ioex.getStackTrace());

        } catch (ClassNotFoundException cnfex) {
            logger.error("SERVER ERROR - " + cnfex.getMessage()
                    + "\nAT: " + cnfex.getStackTrace());
        }
    }

    /**
     * Log events received from Client side and delegates to
     * respected log levels with Client log information to save
     * logs on the server side.
     */
    public void logEvent(Object logEvent) {
        Level loglevel;
        String logMessage, log;

        LogEvent event = (LogEvent) logEvent;

        loglevel = event.getLevel();
        logMessage = event.getMessage().getFormattedMessage();
        log = "CLIENT LOG - [" + event.getThreadName() + "] "+ "{" + event.getSource() + "}" + logMessage;

        if(loglevel.equals(Level.ERROR))
            logger.error(log);

        else if(loglevel.equals(Level.INFO))
            logger.info(log);

        else if(loglevel.equals(Level.WARN))
            logger.warn(log);

    }

    /**
     * Accepts Command from ObjectInputStream socket's Connection and executes as stated
     * Uses a ArrayList<Object> as a wrapper to store the operation to be completed
     * as well as the data to act on for some operation.
     */
    @SuppressWarnings("unchecked")
    public void doOperation(Object operand)
            throws IOException, ClassNotFoundException{

        ArrayList<Object> receivedOp = new ArrayList<>();
        String operation = "", userID = "", issueID= "";
        String toUserID = "", message = "";
        User user;

        boolean success = false;
        int[] stats = new int[3];
        int serviceID = 0;

        logger.info("Processing request from CLIENT");

        receivedOp = (ArrayList<Object>) operand;
        operation = (String) receivedOp.get(0).toString();

        switch(operation) {
            case "AUTHENTICATE":
                handleLogin((User) receivedOp.get(1));
                break;

            case "LOG-OFF":
                handleLogOff();
                break;
        }
    }



    /**
     * Logs user off server by removing current user from list
     * of connected users and closing all connection and input
     * and output streams.
     *
     * @throws IOException
     */
    public void handleLogOff() throws IOException {
        server.removeClientHandler(this);

        try {
            socketConnection.close();
            os.close();
            is.close();
        } catch (IOException ioex) {
            logger.error("SERVER ERROR - " + ioex.getMessage()
                    + "\nAT: " + ioex.getStackTrace());
        }
    }

    /**
     * Logs user that operates in this connection thread to the clientHandler
     * It sends a response to the Client to signify whether correct
     * credentials have been submitted and acts on this information.
     *
     * @param userAuth - The details of the user to be tested for authentication
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void handleLogin(User userAuth)
            throws ClassNotFoundException, IOException{
        boolean success = false;
        logger.info("Handling login information.");
        success = LoginAuth.authLoginUser(userAuth);

        os.writeObject(success);

        logger.info(success +" Access results sent to user.");

        if(success) {
            account = services.UserOperation.getUserInfo(userAuth.getCustomerID(), userAuth.getAccountType());
            account.setAccountType(userAuth.getAccountType());
        }else
            server.removeClientHandler(this);
    }

}








