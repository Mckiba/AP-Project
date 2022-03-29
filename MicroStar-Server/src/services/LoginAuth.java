package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factories.DBConnectorFactory;
import model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginAuth {

    private static final Logger logger = LogManager.getLogger(services.LoginAuth.class);

    public static boolean authLoginUser(User user) {
        String userType = user.getAccountType().toUpperCase();

        if(userType.equals("STUDENT"))
            return loginCustomer(user.getCustomerID(), user.getPassword());

        else if(userType.equals("REP"))
            return loginRep(user.getCustomerID(), user.getPassword());

        else if(userType.equals("AGENT"))
            return loginTech(user.getCustomerID(), user.getPassword());

        return false;

    }


    public static boolean loginTech(String username, String password) {

        String agentID = "", pass = "";

        String loginSql = "SELECT staff_id, password FROM MicroStar.`Technician` "
                + "WHERE staff_id = ? AND password = ?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){
            PreparedStatement statement = dbConn.prepareStatement(loginSql);
            statement.setString(1, username);
            statement.setString(2, password);


            logger.warn("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            logger.info("Technician Results Received");
            while(result.next()) {
                agentID = result.getString(1);
                pass = result.getString(2);

                if(agentID.equals(username) && pass.equals(password))
                    return true;
            }

        } catch (SQLException e) {
            logger.error("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return false;
    }

    public static boolean loginRep(String username, String password) {

        String repID, pass = "";

        String loginSql = "SELECT rep_id, full_name, email, complaint_code, password FROM MicroStar.`CustomerRep` WHERE rep_id = ? AND password =?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){
            PreparedStatement statement = dbConn.prepareStatement(loginSql);
            statement.setString(1, username);
            statement.setString(2, password);

            logger.warn("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            logger.info("REPRESENTATIVE Results Received");
            while(result.next()) {
                repID = result.getString(1);
                pass = result.getString(2);

                if(repID.equals(username) && pass.equals(password))
                    return true;
            }

        } catch (SQLException e) {
            logger.error("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return false;
    }


    public static boolean loginCustomer(String username, String password) {

        String studentID, pass = "";

        String loginSql = "SELECT customer_id, full_name, email, contact, service_address, complaint_code, account_number, password, password FROM MicroStar.`customers` WHERE customer_id = ? AND password =?";

        try (Connection dbConn = DBConnectorFactory.getDatabaseConnection()){
            PreparedStatement statement = dbConn.prepareStatement(loginSql);
            statement.setString(1, username);
            statement.setString(2, password);

            logger.warn("Receiving results from executed Prepared Statement, Error May Occur");
            ResultSet result = statement.executeQuery();

            logger.info("User Results Received");

            System.out.println("Login User");

            while(result.next()) {
                studentID = result.getString(1);
                pass = result.getString(2);

                if(studentID.equals(username) && pass.equals(password))
                    return true;
            }

        } catch (SQLException e) {
            logger.error("Error(" + e.getErrorCode()
                    + ") " + e.getMessage());
        }

        return false;
    }
}
